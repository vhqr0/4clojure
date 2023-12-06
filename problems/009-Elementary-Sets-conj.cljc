;; When operating on a set, the conj function returns a new set with one or more keys "added".

(defn test [__]
  (and
   (= #{1 2 3 4} (conj #{1 4 3} __))))

(defn f [])

(println (testf f))