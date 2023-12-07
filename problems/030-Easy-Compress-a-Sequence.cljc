;; Write a function which removes consecutive duplicates from a sequence.

(defn testf [__]
  (and
   (= (apply str (__ "Leeeeeerrroyyy")) "Leroy")
   (= (__ [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
   (= (__ [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))))

(defn f [coll]
  (->> (cons nil coll)
       (partition 2 1)
       (filter (fn [[x y]] (not= x y)))
       (map last)))

(println (testf f))
