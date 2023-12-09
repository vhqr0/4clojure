;; Write a function which duplicates each element of a sequence.

(defn testf [__]
  (and
   (= (__ [1 2 3]) '(1 1 2 2 3 3))
   (= (__ [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
   (= (__ [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
   (= (__ [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))))

;; (defn f [coll] (interleave coll coll))

(defn f [coll]
  (if (empty? coll)
    ()
    (lazy-seq
     (cons (first coll) (cons (first coll) (f (rest coll)))))))

(println (testf f))
