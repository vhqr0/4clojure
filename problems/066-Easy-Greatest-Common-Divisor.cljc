;; Given two integers, write a function which returns the greatest common divisor.

(defn testf [__]
  (and
   (= (__ 2 4) 2)
   (= (__ 10 5) 5)
   (= (__ 5 7) 1)
   (= (__ 1023 858) 33)))

(def f)

(println (testf f))