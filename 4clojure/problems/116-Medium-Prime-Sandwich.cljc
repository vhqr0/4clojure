;; A <a href="http://en.wikipedia.org/wiki/Balanced_prime">balanced prime</a> is a prime number which is also the mean of
;; the primes directly before and after it in the sequence of valid primes. Create a function which takes an integer n, and
;; returns true iff it is a balanced prime.

(defn testf [__]
  (and
   (= false (__ 4))
   (= true (__ 563))
   (= 1103 (nth (filter __ (range)) 15))))

(defn f [n]
  (letfn [(iterate-prime [i]
            (if (some #(= (rem i %1) 0) (range 2 (inc (clojure.math/sqrt i))))
              (recur (inc i))
              (lazy-seq (cons i (iterate-prime (inc i))))))
          (iterate-balanced-prime []
            (->> (concat [2 3 5 7] (iterate-prime 11))
                 (partition 3 1)
                 (filter (fn [[x y z]] (= (+ x z) (* 2 y))))
                 (map #(nth %1 1))))
          (balanced-prime? [n coll]
            (cond (= n (first coll)) true
                  (< n (first coll)) false
                  true (recur n (rest coll))))]
    (balanced-prime? n (iterate-balanced-prime))))

(println (testf f))
