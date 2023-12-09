;; Two numbers are coprime if their greatest common divisor equals 1. Euler's totient function f(x) is defined as the
;; number of positive integers less than x which are coprime to x. The special case f(1) equals 1. Write a function which
;; calculates Euler's totient function.

(defn testf [__]
  (and
   (= (__ 1) 1)
   (= (__ 10) (count '(1 3 7 9)) 4)
   (= (__ 40) 16)
   (= (__ 99) 60)))

(defn f [x]
  (letfn [(gcd [x y]
            (cond (> x y) (recur (- x y) y)
                  (< x y) (recur (- y x) x)
                  true x))]
    (if (= x 1)
      1
      (count (filter #(= (gcd %1 x) 1) (range 1 x))))))

(println (testf f))
