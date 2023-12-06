;; Lists can be constructed with either a function or a quoted form.

(defn test [__]
  (and
   (= (list __) '(:a :b :c))))

(defn f [])

(println (testf f))