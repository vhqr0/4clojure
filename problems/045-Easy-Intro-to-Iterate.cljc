;; The iterate function can be used to produce an infinite lazy sequence.

(defn testf [__]
  (and
   (= __ (take 5 (iterate #(+ 3 %) 1)))))

(def f)

(println (testf f))