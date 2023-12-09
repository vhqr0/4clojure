;; When operating on a Vector, the conj function will return a new vector with one or more items "added" to the end.

(defn testf [__]
  (and
   (= __ (conj [1 2 3] 4))
   (= __ (conj [1 2] 3 4))))

(def f [1 2 3 4])

(println (testf f))
