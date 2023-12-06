;; A recursive function is a function which calls itself. This is one of the fundamental techniques used in functional
;; programming.

(defn testf [__]
  (and
   (= __ ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5))))

(def f)

(println (testf f))