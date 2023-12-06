;; Write a function which calculates the <a href="http://en.wikipedia.org/wiki/Least_common_multiple">least common
;; multiple</a>. Your function should accept a variable number of positive integers or ratios.

(defn test [__]
  (and
   (== (__ 2 3) 6)
   (== (__ 5 3 7) 105)
   (== (__ 1/3 2/5) 2)
   (== (__ 3/4 1/6) 3/2)
   (== (__ 7 5/7 2 3/5) 210)))

(defn f [])

(println (testf f))