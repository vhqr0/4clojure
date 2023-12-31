;; Write a function which creates a list of all integers in a given range.

;; restricted: range 

(defn testf [__]
  (and
   (= (__ 1 4) '(1 2 3))
   (= (__ -2 2) '(-2 -1 0 1))
   (= (__ 5 8) '(5 6 7))))

(defn f [x y]
  (if (>= x y)
    ()
    (lazy-seq
     (cons x (f (inc x) y)))))

(println (testf f))
