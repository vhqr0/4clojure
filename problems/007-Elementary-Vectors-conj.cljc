;; When operating on a Vector, the conj function will return a new vector with one or more items "added" to the end.

(defn test [__]
  (and
   (= __ (conj [1 2 3] 4))
   (= __ (conj [1 2] 3 4))))

(defn f [])

(println (testf f))