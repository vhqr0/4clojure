;; Write a function which packs consecutive duplicates into sub-lists.

(defn testf [__]
  (and
   (= (__ [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
   (= (__ [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
   (= (__ [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))))

(def f)

(println (testf f))