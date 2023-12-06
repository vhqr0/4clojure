;; Write a function which multiplies two numbers and returns the result as a sequence of its digits.

(defn testf [__]
  (and
   (= (__ 1 1) [1])
   (= (__ 99 9) [8 9 1])
   (= (__ 999 99) [9 8 9 0 1])))

(def f)

(println (testf f))