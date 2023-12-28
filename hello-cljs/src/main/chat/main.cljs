(ns chat.main
  (:require-macros [clojure.core.async :refer [go go-loop]]
                   [chat.macros :refer [go-loop-sub]])
  (:require [clojure.string :as str]
            [clojure.core.async :as async]
            [datascript.core :as d]
            [chat.util :as u]
            [chat.server :as server]
            [chat.ui :as ui]))

(enable-console-print!)

(def ^:dynamic *room-msg-limit* 30)

(def conn (d/create-conn {:room/title {}
                          :message/room {:db/valueType :db.type/ref}
                          :message/text {}
                          :message/author {:db/valueType :db.type/ref}
                          :message/timestamp {}
                          :message/unread {}
                          :user/name {}
                          :user/avatar {}
                          :user/me {}
                          :user/state {}}))

(def event-bus (async/chan))
(def event-bus-pub (async/pub event-bus first))

(go-loop-sub event-bus-pub :send-msg [_ text]
             (when-not (str/blank? text)
               (let [db @conn
                     msg {:message/room (u/q1-by db :room/selected)
                          :message/author (u/q1-by db :user/me)
                          :message/text text}]
                 (server/send msg))))

(go-loop-sub event-bus-pub :recv-msg [_ msg]
             (let [room (u/q1-by @conn :room/selected)
                   msg (if (== (:message/room msg) room)
                         (dissoc msg :message/unread)
                         msg)]
               (d/transact! conn [msg])))

(defn- user-stub [uid]
  {:db/id uid
   :user/name "Loading..."
   :user/avatar "avatars/loading.jpg"
   :user/state :loading})

(defn- load-user [uid]
  (server/call server/get-user [uid]
               (fn [user]
                 (d/transact! conn [(assoc user
                                           :user/state :loaded)]))))

(let [ch (async/chan)]
  (async/sub event-bus-pub :recv-msg ch)
  (go-loop [loaded-users #{}]
    (let [[_ msg] (<! ch)
          uid (:message/author msg)]
      (if (contains? loaded-users uid)
        (recur loaded-users)
        (do
          (when-not (d/q '[:find ?e .
                           :in $ ?e
                           :where [?e :user/state :loaded]]
                         @conn uid)
            (d/transact! conn [(user-stub uid)])
            (load-user uid))
          (recur (conj loaded-users uid)))))))

(defn- select-room [db room-id]
  (let [selected (d/q '[:find ?r .
                        :where [?r :room/selected true]] db)]
    (case selected
      nil [[:db/add room-id :room/selected true]]
      room-id []
      [[:db/retract selected :room/selected true]
       [:db/add room-id :room/selected true]])))

(go-loop-sub event-bus-pub :select-room [_ room-id]
             (d/transact! conn [[:db.fn/call select-room room-id]]))

(defn- mark-read [db room-id]
  (let [unread (d/q '[:find [?m ...]
                      :in $ ?r
                      :where [?m :message/room ?r]]
                    db room-id)]
    (map (fn [mid] [:db/retract mid :message/unread true]) unread)))

(go-loop-sub event-bus-pub :select-room [_ room-id]
             (d/transact! conn [[:db.fn/call mark-read room-id]]))

(go-loop-sub event-bus-pub :recv-msg [_ msg]
             (let [db @conn
                   room-id (:message/room msg)
                   keep-msgs (->> (d/q '[:find (max ?lim ?m) .
                                         :in $ ?room-id ?lim
                                         :where [?m :message/room ?room-id]]
                                       db
                                       room-id
                                       *room-msg-limit*)
                                  (set))
                   remove-msgs (d/q '[:find [?m ...]
                                      :in $ ?room-id ?remove-pred
                                      :where [?m :message/room ?room-id]
                                      [(?remove-pred ?m)]]
                                    db
                                    room-id
                                    #(not (contains? keep-msgs %)))]
               (d/transact! conn
                            (map #(vector :db.fn/retractEntity %) remove-msgs))))

(defn main [& _]
  (ui/mount conn event-bus)
  (server/call server/get-rooms []
               (fn [rooms]
                 (d/transact! conn rooms)
                 (async/put! event-bus [:select-room (:db/id (first rooms))])))
  (server/call server/whoami []
               (fn [user]
                 (d/transact! conn [(assoc user
                                           :user/me true
                                           :user/state :loaded)])))
  (server/subscribe
   (fn [message]
     (async/put! event-bus [:recv-msg message]))))
