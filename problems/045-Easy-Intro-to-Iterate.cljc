;; The iterate function can be used to produce an infinite lazy sequence.

(defn test [__]
  (and
   (= __ (take 5 (iterate #(+ 3 %) 1)))))

(defn f [])

(println (testf f))