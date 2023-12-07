;; Write a function which calculates factorials.

(defn testf [__]
  (and
   (= (__ 1) 1)
   (= (__ 3) 6)
   (= (__ 5) 120)
   (= (__ 8) 40320)))

(defn f [x] (reduce * (range 2 (inc x))))

(println (testf f))
