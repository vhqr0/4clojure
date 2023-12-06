;; Write a function which packs consecutive duplicates into sub-lists.

(defn testf [__]
  (and
   (= (__ [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
   (= (__ [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
   (= (__ [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))))

(defn f [coll] ((fn [rcoll coll xcoll x] (cond (empty? coll) (conj rcoll xcoll) (= (first coll) x) (recur rcoll (rest coll) (conj xcoll x) x) true (recur (conj rcoll xcoll) (rest coll) (list (first coll)) (first coll)))) [] (rest coll) (list (first coll)) (first coll)))

(println (testf f))
