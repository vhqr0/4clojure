;; Let bindings and function parameter lists support destructuring.

(defn testf [__]
  (and
   (= [2 4] (let [[a b c d e] [0 1 2 3 4]] __))))

;; (def f [c e])
(def f [2 4])

(println (testf f))
