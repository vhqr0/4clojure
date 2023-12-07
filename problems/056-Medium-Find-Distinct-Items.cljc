;; Write a function which removes the duplicates from a sequence. Order of the items must be maintained.

;; restricted: distinct 

(defn testf [__]
  (and
   (= (__ [1 2 1 3 1 2 4]) [1 2 3 4])
   (= (__ [:a :a :b :b :c :c]) [:a :b :c])
   (= (__ '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))
   (= (__ (range 50)) (range 50))))

(defn f [coll]
  (letfn [(remove-duplicate [s coll]
            (cond (empty? coll) ()
                  (s (first coll)) (recur s (rest coll))
                  true (lazy-seq (cons (first coll) (remove-duplicate (conj s (first coll)) (rest coll))))))]
    (remove-duplicate #{} coll)))

(println (testf f))
