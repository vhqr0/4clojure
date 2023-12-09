;; Write a function which takes a collection of integers as an argument. Return the count of how many elements are smaller
;; than the sum of their squared component digits. For example: 10 is larger than 1 squared plus 0 squared; whereas 15 is
;; smaller than 1 squared plus 5 squared.

(defn testf [__]
  (and
   (= 8 (__ (range 10)))
   (= 19 (__ (range 30)))
   (= 50 (__ (range 100)))
   (= 50 (__ (range 1000)))))

(defn f [coll]
  (letfn [(iterate-digits [n base]
            (if (zero? n)
              ()
              (lazy-seq (cons (rem n base) (iterate-digits (quot n base) base)))))
          (smaller-than-squared-sum? [n]
            (< n (->> (iterate-digits n 10)
                      (map #(* %1 %1))
                      (reduce +))))]
    (count (filter smaller-than-squared-sum? coll))))

(println (testf f))
