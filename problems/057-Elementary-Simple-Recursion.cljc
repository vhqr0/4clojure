;; A recursive function is a function which calls itself. This is one of the fundamental techniques used in functional
;; programming.

(defn test [__]
  (and
   (= __ ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5))))

(defn f [])

(println (testf f))