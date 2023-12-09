;; Happy numbers are positive integers that follow a particular formula: take each individual digit, square it, and then
;; sum the squares to get a new number. Repeat with the new number and eventually, you might get to a number whose squared
;; sum is 1. This is a happy number. An unhappy number (or sad number) is one that loops endlessly. Write a function that
;; determines if a number is happy or not.

(defn testf [__]
  (and
   (= (__ 7) true)
   (= (__ 986543210) true)
   (= (__ 2) false)
   (= (__ 3) false)))

(defn f [n]
  (letfn [(iterate-digits [n base]
            (if (zero? n)
              ()
              (lazy-seq (cons (rem n base) (iterate-digits (quot n base) base)))))
          (happy-number? [s n]
            (cond (= n 1) true
                  (s n) false
                  true (recur (conj s n) (apply + (map #(* %1 %1) (iterate-digits n 10))))))]
    (happy-number? #{} n)))

(println (testf f))
