;; Write a function which returns the sum of a sequence of numbers.

(defn testf [__]
  (and
   (= (__ [1 2 3]) 6)
   (= (__ (list 0 -2 5 5)) 8)
   (= (__ #{4 2 1}) 7)
   (= (__ '(0 0 -1)) -1)
   (= (__ '(1 10 3)) 14)))

(def f (partial reduce +))

(println (testf f))
