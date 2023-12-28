(ns chat.server
  (:require-macros [clojure.core.async :refer [go go-loop]])
  (:require [clojure.core.async :as async]
            [clojure.edn :as edn]
            [datascript.core :as d]
            [chat.util :as u])
  (:import [goog.net XhrIo]))

(def fixtures [{:room/title "World domination"     :room/source "rooms/world_domination.edn"}
               {:room/title "Pussies"              :room/source "rooms/pussies.edn"}
               {:room/title "Internet of cats"     :room/source "rooms/cats.edn"}
               {:room/title "Paw care"             :room/source "rooms/paw_care.edn"}
               {:room/title "Puss in Boots sequel" :room/source "rooms/puss_in_boots.edn"}
               {:room/title "Afterlife"            :room/source "rooms/afterlife.edn"}
               {:user/name "Starry"                :user/avatar "avatars/a1.jpg"}
               {:user/name "Friar Tuck"            :user/avatar "avatars/a2.jpg"}
               {:user/name "Toom"                  :user/avatar "avatars/a3.jpg"}
               {:user/name "Hansel"                :user/avatar "avatars/a4.jpg"}
               {:user/name "Cuddlebug"             :user/avatar "avatars/a5.jpg"}
               {:user/name "Georgie"               :user/avatar "avatars/a6.jpg"}
               {:user/name "Jean-Paul Gizmondo"    :user/avatar "avatars/a7.jpg"}
               {:user/name "Gorgeous Furboy"       :user/avatar "avatars/a8.jpg"}
               {:user/name "Jiggle Belly"          :user/avatar "avatars/a9.jpg"}
               {:user/name "Invitation"            :user/avatar "avatars/a10.jpg"}
               {:user/name "The Phantom"           :user/avatar "avatars/a11.jpg"}
               {:user/name "Rupert"                :user/avatar "avatars/a12.jpg"}
               {:user/name "Obstinate"             :user/avatar "avatars/a13.jpg"}
               {:user/name "Bunter"                :user/avatar "avatars/a14.jpg"}
               {:user/name "Porsche"               :user/avatar "avatars/a15.jpg"}
               {:user/name "Puka"                  :user/avatar "avatars/a16.jpg"}
               {:user/name "Tabba To"              :user/avatar "avatars/a17.jpg"}
               {:user/name "Artful Dodger"         :user/avatar "avatars/a18.jpg"}
               {:user/name "Half Hot Chocolate"    :user/avatar "avatars/a19.jpg"}
               {:user/name "Budmeister"            :user/avatar "avatars/a20.jpg"}
               {:user/name "Scsi2"                 :user/avatar "avatars/a21.jpg"}
               {:user/name "BigMouth"              :user/avatar "avatars/a22.jpg"}
               {:user/name "Splinter"              :user/avatar "avatars/a23.jpg"}
               {:user/name "Isidor"                :user/avatar "avatars/a24.jpg"}
               {:user/name "Chanel"                :user/avatar "avatars/a25.jpg"}])

(def conn (d/create-conn {:room/messages {:db/cardinality :db.cardinality/many}}))

(d/transact! conn fixtures)

(defn- ajax [url callback]
  (goog.net.XhrIo.send url
                       (fn [resp]
                         (-> (.-target resp)
                             (.getResponseText)
                             (edn/read-string)
                             (callback)))))

(doseq [[id url title] (u/-q '[:find ?id ?src ?title
                               :where [?id :room/source ?src] [?id :room/title ?title]]
                             @conn)]
  (ajax url
        (fn [msgs]
          (d/transact! conn [{:db/id id :room/messages msgs}]))))



(defn- rand-n [min max]
  (+ min (rand-int (- max min))))

(defn- rand-pred [pred f]
  (let [x (f)]
    (if (pred x) x (recur pred f))))

(defn- rand-user-id [db]
  (d/q '[:find (rand ?id) .
         :where [?id :user/name]]
       db))

(defn- rand-room [db]
  (d/q '[:find (rand ?id) .
         :where [?id :room/title]]
       db))

(defn- rand-message [db room-id]
  (-> (d/datoms db :aevt :room/messages room-id)
      (rand-nth)
      (:v)))

(defn- user-by-id [db id]
  (-> (d/entity db id)
      (select-keys [:db/id :user/name :user/avatar])))



(def ^:private me (atom nil))

(defn- later [f]
  (js/setTimeout f (rand-n 1000 2000)))

(defn call [f args callback]
  (later #(callback (apply f args))))

(defn get-rooms []
  (->> (u/-q '[:find ?id ?title
               :where [?id :room/title ?title]]
             @conn)
       (mapv #(zipmap [:db/id :room/title] %))))

(defn get-user [id]
  (user-by-id @conn id))

(defn whoami []
  (let [db @conn
        id (or @me (reset! me (rand-user-id db)))]
    (user-by-id db id)))



(def ^:private next-msg-id (atom 10000))
(def ^:private msgs-chan (async/chan))

(defn send [msg]
  (async/put! msgs-chan
              (assoc msg
                     :db/id (swap! next-msg-id inc)
                     :message/timestamp (js/Date.))))

(go-loop []
  (<! (async/timeout (rand-n 500 1500)))
  (let [db @conn
        room-id (rand-room db)
        text (rand-message db room-id)
        author-id (rand-pred #(not= % @me) #(rand-user-id db))
        msg {:db/id (swap! next-msg-id inc)
             :message/text text
             :message/author author-id
             :message/room room-id
             :message/unread true
             :message/timestamp (js/Date.)}]
    (when text
      (>! msgs-chan msg))
    (recur)))

(defn subscribe [on-msg]
  (go-loop []
    (let [msg (<! msgs-chan)]
      (on-msg msg))
    (recur)))
