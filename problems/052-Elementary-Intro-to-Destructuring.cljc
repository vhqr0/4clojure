;; Let bindings and function parameter lists support destructuring.

(defn test [__]
  (and
   (= [2 4] (let [[a b c d e] [0 1 2 3 4]] __))))

(defn f [])

(println (testf f))