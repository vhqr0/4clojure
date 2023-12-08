;; Write a function which returns the intersection of two sets. The intersection is the sub-set of items that each set has
;; in common.

;; restricted: intersection 

(defn testf [__]
  (and
   (= (__ #{0 1 2 3} #{2 3 4 5}) #{2 3})
   (= (__ #{0 1 2} #{3 4 5}) #{})
   (= (__ #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d})))

(defn f [& ss]
  (->> ss
       (reduce into #{})
       (filter (fn [x] (every? #(%1 x) ss)))
       (into #{})))

(println (testf f))
