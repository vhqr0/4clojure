;; Write a function which returns only the odd numbers from a sequence.

(defn testf [__]
  (and
   (= (__ #{1 2 3 4 5}) '(1 3 5))
   (= (__ [4 2 1 6]) '(1))
   (= (__ [2 2 4 6]) '())
   (= (__ [1 1 1 3]) '(1 1 1 3))))

;; (def f (partial filter odd?))

(defn f [coll]
    (cond (empty? coll) ()
          (even? (first coll)) (recur (rest coll))
          true (lazy-seq (cons (first coll) (f (rest coll))))))

(println (testf f))
