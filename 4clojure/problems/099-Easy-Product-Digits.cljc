;; Write a function which multiplies two numbers and returns the result as a sequence of its digits.

(defn testf [__]
  (and
   (= (__ 1 1) [1])
   (= (__ 99 9) [8 9 1])
   (= (__ 999 99) [9 8 9 0 1])))

(defn f [& ns]
  (letfn [(iterate-digits [n base]
            (if (zero? n)
              ()
              (lazy-seq (cons (rem n base) (iterate-digits (quot n base) base)))))]
    (reverse (iterate-digits (apply * ns) 10))))

(println (testf f))
