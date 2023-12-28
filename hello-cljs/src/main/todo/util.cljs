(ns todo.util
  (:require [datascript.core :as d]))

(def ^:dynamic *debug-q* false)

(defn -q [q & args]
  (if *debug-q*
    (let [key (str q)
          _ (js/console.time key)
          res (apply d/q q args)
          _ (js/console.timeEnd key)]
      res)
    (apply d/q q args)))

(defn valfilter [f m]
  (reduce-kv (fn [m k v] (if (f v) m (assoc m k v)))
             (empty m) m))

(defn e-by-av [db a v]
  (-> (d/datoms db :avet a v) first :e))

(defn date->month [date]
  [(.getFullYear date) (inc (.getMonth date))])

(def month-int->month-str
  ["_"
   "January"
   "February"
   "March"
   "April"
   "May"
   "June"
   "July"
   "August"
   "September"
   "October"
   "November"
   "December"])

(defn format-month [month year]
  (str (month-int->month-str month) " " year))

(defn month-start [month year]
  (js/Date. year (dec month) 1))

(defn month-end [month year]
  (let [[month year] (if (< month 12)
                       [(inc month) year]
                       [1 (inc year)])]
    (-> (js/Date. year (dec month) 1)
        .getTime
        dec
        js/Date.)))

(def fixtures [
               [:db/add 1 :system/group :all]

               {:db/id -1 :project/name "datascript"}
               {:db/id -2 :project/name "nyc-webinar"}
               {:db/id -3 :project/name "shopping"}
               
               {:todo/text "Displaying list of todos"
                :todo/tags ["listen" "query"]
                :todo/project -2
                :todo/done true
                :todo/due  #inst "2014-12-13"}
               {:todo/text "Persisting to localStorage"
                :todo/tags ["listen" "serialization" "transact"]
                :todo/project -2
                :todo/done true
                :todo/due  #inst "2014-12-13"}
               {:todo/text "Make task completable"
                :todo/tags ["transact" "funs"]
                :todo/project -2
                :todo/done false
                :todo/due  #inst "2014-12-13"}
               {:todo/text "Fix fn calls on emtpy rels"
                :todo/tags ["bug" "funs" "query"]
                :todo/project -1
                :todo/done false
                :todo/due  #inst "2015-01-01"}
               {:todo/text "Add db filtering"
                :todo/project -1
                :todo/done false
                :todo/due  #inst "2015-05-30"}
               {:todo/text "Soap"
                :todo/project -3
                :todo/done false
                :todo/due  #inst "2015-05-01"}
               {:todo/text "Cake"
                :todo/done false
                :todo/project -3}
               {:todo/text "Just a task"
                :todo/done false}
               {:todo/text "Another incomplete task"
                :todo/done false}])
