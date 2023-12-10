;; Write a function which returns the first x number of prime numbers.

(defn testf [__]
  (and
   (= (__ 2) [2 3])
   (= (__ 5) [2 3 5 7 11])
   (= (last (__ 100)) 541)))

(defn f [n]
  (letfn [(iterate-prime [i]
            (if (some #(= (rem i %1) 0) (range 2 (inc (clojure.math/sqrt i))))
              (recur (inc i))
              (lazy-seq (cons i (iterate-prime (inc i))))))]
    (take n (concat [2 3 5 7] (iterate-prime 11)))))

(println (testf f))
