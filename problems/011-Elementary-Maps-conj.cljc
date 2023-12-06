;; When operating on a map, the conj function returns a new map with one or more key-value pairs "added".

(defn test [__]
  (and
   (= {:a 1, :b 2, :c 3} (conj {:a 1} __ [:c 3]))))

(defn f [])

(println (testf f))