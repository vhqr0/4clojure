(ns todo.main
  (:require [clojure.set :as set]
            [clojure.string :as str]
            [rum.core :as rum]
            [datascript.core :as d]
            [datascript.transit :as dt]
            [todo.dom :as dom]
            [todo.util :as u]))

(enable-console-print!)

(def schema {:todo/tags    {:db/cardinality :db.cardinality/many}
             :todo/project {:db/valueType :db.type/ref}
             :todo/done    {:db/index true}
             :todo/due     {:db/index true}
             :project/name {:db/index true}})

(defonce conn (d/create-conn schema))

(defn set-system-attrs! [& args]
  (d/transact! conn
               (for [[attr value] (partition 2 args)]
                 (if value
                   [:db/add 1 attr value]
                   [:db.fn/retractAttribute 1 attr]))))

(defn system-attr
  ([db attr]
   (get (d/entity db 1) attr))
  ([db attr & attrs]
   (mapv #(system-attr db %) (concat [attr] attrs))))

(rum/defc filter-pane [db]
  [:.filter-pane
   [:input.filter {:type "text"
                   :value (or (system-attr db :system/filter) "")
                   :on-change (fn [_]
                                (set-system-attrs! :system/filter (dom/value (dom/q ".filter"))))
                   :placeholder "Filter"}]])

(def filter-rule
  '[[(match ?todo ?term)
     [?todo :todo/project ?p]
     [?p :project/name ?term]]
    [(match ?todo ?term)
     [?todo :todo/tags ?term]]])

(defn todos-by-filter [db terms]
  (u/-q '[:find [?e ...]
          :in $ % [?term ...]
          :where [?e :todo/text]
          (match ?e ?term)]
        db filter-rule terms))

(defn filter-terms [db]
  (when-let [filter (system-attr db :system/filter)]
    (let [trimmed-filter (str/trim filter)]
      (when-not (str/blank? trimmed-filter)
        (str/split filter #"\s+")))))

(defn filtered-db [db]
  (if-let [terms (filter-terms db)]
    (let [whitelist (set (todos-by-filter db terms))
          pred (fn [db datom]
                 (or (not= "todo" (namespace (:a datom)))
                     (contains? whitelist (:e datom))))]
      (d/filter db pred))
    db))

(defmulti todos-by-group (fn [db group item] group))

(defmethod todos-by-group :inbox [db _ _]
  (u/-q '[:find [?todo ...]
          :where
          [?todo :todo/text]
          [(get-else $ ?todo :todo/project :none) ?project]
          [(get-else $ ?todo :todo/due :none) ?due]
          [(= ?project :none)]
          [(= ?due :none)]]
        db))

(defmethod todos-by-group :completed [db _ _]
  (u/-q '[:find [?todo ...]
          :where [?todo :todo/done true]]
        db))

(defmethod todos-by-group :all [db _ _]
  (u/-q '[:find [?todo ...]
          :where [?todo :todo/text]]
        db))

(defmethod todos-by-group :project [db _ pid]
  (u/-q '[:find [?todo ...]
          :in $ ?pid
          :where [?todo :todo/project ?pid]]
        db pid))

(defmethod todos-by-group :month [db _ [year month]]
  (u/-q '[:find [?todo ...]
          :in $ ?from ?to
          :where [?todo :todo/due ?due] [(<= ?from ?due ?to)]]
        db (u/month-start month year) (u/month-end month year)))

(rum/defc group-item [db title group item]
  (let [todos (todos-by-group db group item)
        count (u/-q '[:find (count ?todo) .
                      :in $ [?todo ...]
                      :where [?todo :todo/done false]]
                    db todos)]
    [:.group-item {:class (when (= [group item]
                                   (system-attr db :system/group :system/group-item))
                            "group-item_selected")}
     [:span {:on-click (fn [_]
                         (set-system-attrs! :system/group group
                                            :system/group-item item))}
      title]
     (when count
       (:span.group-item-count count))]))

(rum/defc plan-group [db]
  [:.group
   [:.group-title "Plan"]
   (for [[year month] (->> (u/-q '[:find [?month ...]
                                   :in $ ?date->month
                                   :where
                                   [?todo :todo/due ?date]
                                   [(?date->month ?date) ?month]]
                                 db u/date->month)
                           sort)]
     (rum/with-key (group-item db (u/format-month month year) :month [year month]) (+ year month)))])

(rum/defc projects-group [db]
  [:.group
   [:.group-title "Projects"]
   (for [[pid name] (->> (u/-q '[:find ?pid ?project
                                 :where
                                 [?todo :todo/project ?pid]
                                 [?pid :project/name ?project]]
                               db)
                         (sort-by second))]
     (rum/with-key (group-item db name :project pid) pid))])

(rum/defc overview-pane [db]
  [:.overview-pane
   [:.group
    (group-item db "Inbox" :inbox nil)
    (group-item db "Completed" :completed nil)
    (group-item db "All" :all nil)]
   (plan-group db)
   (projects-group db)])

(defn toggle-todo-tx [db eid]
  (let [done? (:todo/done (d/entity db eid))]
    [[:db/add eid :todo/done (not done?)]]))

(defn toggle-todo [eid]
  (d/transact! conn [[:db.fn/call toggle-todo-tx eid]]))

(rum/defc todo-pane [db]
  [:.todo-pane
   (let [todos (let [[group item] (system-attr db :system/group :system/group-item)]
                 (todos-by-group db group item))]
     (for [eid (sort todos)
           :let [td (d/entity db eid)]]
       [:.todo {:key (:db/id td)
                :class (if (:todo/done td) "todo_done" "")}
        [:.todo-checkbox {:key "checkbox" :on-click #(toggle-todo eid)} "X"]
        [:.todo-text {:key "text"} (:todo/text td)]
        [:.todo-subtext {:key "subtext"}
         (when-let [due (:todo/due td)]
           [:span {:key "due"} (.toDateString due)])
         (when-let [project (:todo/project td)]
           [:span {:key "project"} (:project/name project)])
         (for [tag (:todo/tags td)]
           [:span {:key tag} tag])]]))])

(defn extract-todo []
  (when-let [text (dom/value (dom/q ".add-text"))]
    {:text text
     :project (dom/value (dom/q ".add-project"))
     :due (dom/date-value (dom/q ".add-due"))
     :tags (dom/array-value (dom/q ".add-tags"))}))

(defn clean-todo []
  (dom/set-value! (dom/q ".add-text") nil)
  (dom/set-value! (dom/q ".add-project") nil)
  (dom/set-value! (dom/q ".add-due") nil)
  (dom/set-value! (dom/q ".add-tags") nil))

(defn add-todo []
  (when-let [todo (extract-todo)]
    (let [project (:project todo)
          project-id (when project (u/e-by-av @conn :project/name project))
          project-tx (when (and project (nil? project-id))
                       [[:db/add -1 :project/name project]])
          entity (->> {:todo/text (:text todo)
                       :todo/done false
                       :todo/project (when project (or project-id -1))
                       :todo/due (:due todo)
                       :todo/tags (:tags todo)}
                      (u/valfilter nil?))]
      (d/transact! conn (concat project-tx [entity])))
    (clean-todo)))

(rum/defc add-view []
  [:div.add-view
   [:input.add-text    {:type "text" :placeholder "New Task"}]
   [:input.add-project {:type "text" :placeholder "Project"}]
   [:input.add-tags    {:type "text" :placeholder "Tags"}]
   [:input.add-due     {:type "text" :placeholder "Due Date"}]
   [:button.add-submit {:on-click (fn [_] (add-todo))} "Add Task"]])

(rum/defc canvas < rum/reactive [conn]
  (let [db (rum/react conn)]
    [:.canvas
     [:.main-view
      (rum/with-key (filter-pane db) "filter")
      (let [db (filtered-db db)]
        (list
         (rum/with-key (overview-pane db) "overview")
         (rum/with-key (todo-pane db) "db")))
      (rum/with-key (add-view) "add")]]))

(defn main [& _]
  (d/transact! conn u/fixtures)
  (rum/mount (canvas conn) js/document.body))
