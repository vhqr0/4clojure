(ns chat.util
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

(defn q1-by
  ([db attr]
   (->> (-q '[:find ?e :in $ ?a :where [?e ?a]] db attr) ffirst))
  ([db attr value]
   (->> (-q '[:find ?e :in $ ?a ?v :where [?e ?a ?v]] db attr value) ffirst)))

(defn qe [q db & sources]
  (->> (apply -q q db sources)
       ffirst
       (d/entity db)))

(defn qes [q db & sources]
  (->> (apply -q q db sources)
       (map #(d/entity db (first %)))))

(defn qe-by
  ([db attr]
   (qe '[:find ?e :in $ ?a :where [?e ?a]] db attr))
  ([db attr value]
   (qe '[:find ?e :in $ ?a ?v :where [?e ?a ?v]] db attr value)))

(defn qes-by
  ([db attr]
   (qes '[:find ?e :in $ ?a :where [?e ?a]] db attr))
  ([db attr value]
   (qes '[:find ?e :in $ ?a ?v :where [?e ?a ?v]] db attr value)))

(defn qmap [q & sources]
  (into {} (apply -q q sources)))
