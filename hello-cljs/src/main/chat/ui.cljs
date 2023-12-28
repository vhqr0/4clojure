(ns chat.ui
  (:require [goog.string :as gstr]
            [goog.string.format]
            [clojure.core.async :as async]
            [rum.core :as rum]
            [datascript.core :as d]
            [chat.util :as u]))

(defn- format-time [date]
  (gstr/format "%02d:%02d" (.getHours date) (.getMinutes date)))

(defn- should-scroll? [node]
  (<=
   (- (.-scrollHeight node) (.-scrollTop node) (.-offsetHeight node))
   0))

(defn get-dom-node [state]
  (let [comp (:rum/react-component state)]
    ;; removed since react 0.14
    ;; (.getDOMNode comp)
    (js/ReactDOM.findDOMNode comp)))

(def sticky-mixin
  {:will-update (fn [state]
                  (let [node (get-dom-node state)]
                    (assoc state ::sticky? (should-scroll? node))))
   :did-update (fn [state]
                 (when (::sticky? state)
                   (let [node (get-dom-node state)]
                     (set! (.-scrollTop node) (.-scrollHeight node))))
                 state)})



(defn- select-room [chan rid]
  (async/put! chan [:select-room rid]))

(defn- send-msg [chan text]
  (async/put! chan [:send-msg text]))

(rum/defc avatar [user]
  [:.message__avatar
   [:img {:src (:user/avatar user "avatars/loading.jpg")}]])

(rum/defc room [room last-msg unread event-bus]
  (let [user (:message/author last-msg)]
    [:.topic {:class (when (:room/selected room) "topic_selected")
              :on-click (fn [_]
                          (select-room event-bus (:db/id room))
                          (.focus (.getElementById js/document "compose__area")))}
     (if last-msg
       (list
        [:img.topic__avatar {:key "avatar" :src (:user/avatar user)}]
        [:.topic__title {:key "title"}
         (:room/title room)
         (if (pos? unread)
           [:span.topic__unread (str unread (when (>= unread 30) "+"))]
           [:span])]
        [:.topic__msg {:key "msg"} (:message/text last-msg)]
        [:.topic__ts {:key "ts"}
         (:user/name user) " at " (format-time (:message/timestamp last-msg))])
       [:.topic__title (:room/title room)])]))

(rum/defc rooms-pane [db event-bus]
  (let [rooms (->> (u/qes-by db :room/title)
                   (sort-by :room/title))
        last-msgs (u/qmap '[:find ?r (max ?m)
                            :where [?m :message/room ?r]]
                          db)
        unread-counts (u/qmap '[:find ?r (count ?m)
                                :where [?m :message/unread] [?m :message/room ?r]]
                              db)]
    [:#rooms__pane.pane
     [:#rooms__header.header
      [:.title "Rooms"]]
     (map #(let [rid (:db/id %)]
             [:div {:key rid}
              (room %
                    (when-let [mid (get last-msgs rid)]
                      (d/entity db mid))
                    (get unread-counts rid)
                    event-bus)])
          rooms)]))

(rum/defc text < rum/static [text]
  [:.message__text text])

(rum/defc message [msg]
  (let [user (:message/author msg)]
    [:.message {:class [(when (:message/unread msg) "message_unread")
                        (when (:user/me user) "me")]}
     (avatar user)
     [:.message__name
      (:user/name user)
      [:span.message__ts
       (format-time (:message/timestamp msg))]]
     (text (:message/text msg))]))

(rum/defc chat-pane < sticky-mixin [db]
  (let [[room-id room-title] (d/q '[:find [?r ?t]
                                    :where [?r :room/selected true]
                                    [?r :room/title ?t]] db)
        msgs (->> (u/qes-by db :message/room room-id)
                  (sort-by :message/timestamp))]
    [:#chat__pane.pane
     [:#chat__header.header
      [:.title room-title]]
     (map (fn [msg] [:div {:key (:db/id msg)} (message msg)]) msgs)]))

(defn- textarea-keydown [callback]
  (fn [e]
    (when (and (== (.-keyCode e) 13) (not (.-shiftKey e)))
      (callback (.. e -target -value))
      (set! (.. e -target -value) "")
      (.preventDefault e))))

(rum/defc compose-pane [db event-bus]
  [:#compose
   (avatar (u/qes-by db :user/me true))
   [:textarea#compose__area.message__text
    {:placeholder "Reply..."
     :auto-focus true
     :on-key-down (textarea-keydown #(send-msg event-bus %))}]])

(rum/defc window < rum/reactive [conn event-bus]
  (let [db (rum/react conn)]
    [:#window
     [:#rooms (rooms-pane db event-bus)]
     [:#chat (chat-pane db)]
     (compose-pane db event-bus)]))

(defn mount [conn event-bus]
  (rum/mount (window conn event-bus) js/document.body))
