;; Write a function which behaves like reduce, but returns each intermediate value of the reduction. Your function must
;; accept either two or three arguments, and the return sequence must be lazy.

;; restricted: reductions 

(defn testf [__]
  (and
   (= (take 5 (__ + (range))) [0 1 3 6 10])
   (= (__ conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]])
   (= (last (__ * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120)))

(defn f
  ([g coll] (f g (g (first coll)) (rest coll)))
  ([g init coll]
   (if (empty? coll)
     (list init)
     (lazy-seq
      (cons init (f g (g init (first coll)) (rest coll)))))))

(println (testf f))
