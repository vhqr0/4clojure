;; Write a function which returns the first X fibonacci numbers.

(defn testf [__]
  (and
   (= (__ 3) '(1 1 2))
   (= (__ 6) '(1 1 2 3 5 8))
   (= (__ 8) '(1 1 2 3 5 8 13 21))))

(def f)

(println (testf f))