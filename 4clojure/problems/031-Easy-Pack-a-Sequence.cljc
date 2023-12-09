;; Write a function which packs consecutive duplicates into sub-lists.

(defn testf [__]
  (and
   (= (__ [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
   (= (__ [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
   (= (__ [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))))

;; (def f (partial partition-by identity))

(defn f [coll]
  (letfn [(split-duplicate [rcoll coll]
            (cond (empty? coll) [rcoll coll]
                  (not= (first coll) (first rcoll)) [rcoll coll]
                  true (recur (cons (first coll) rcoll) (rest coll))))]
    (if (empty? coll)
      ()
      (let [[rcoll coll] (split-duplicate (list (first coll)) (rest coll))]
        (lazy-seq (cons rcoll (f coll)))))))

(println (testf f))
