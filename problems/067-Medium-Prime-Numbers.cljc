;; Write a function which returns the first x number of prime numbers.

(defn testf [__]
  (and
   (= (__ 2) [2 3])
   (= (__ 5) [2 3 5 7 11])
   (= (last (__ 100)) 541)))

(def f)

(println (testf f))