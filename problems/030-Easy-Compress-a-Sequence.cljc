;; Write a function which removes consecutive duplicates from a sequence.

(defn testf [__]
  (and
   (= (apply str (__ "Leeeeeerrroyyy")) "Leroy")
   (= (__ [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
   (= (__ [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))))

(defn f [coll] ((fn [rcoll coll x] (cond (empty? coll) rcoll (= (first coll) x) (recur rcoll (rest coll) x) true (recur (conj rcoll (first coll)) (rest coll) (first coll)))) [] coll nil))

(println (testf f))
