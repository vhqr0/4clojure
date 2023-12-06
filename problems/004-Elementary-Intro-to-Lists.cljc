;; Lists can be constructed with either a function or a quoted form.

(defn testf [__]
  (and
   (= (list __) '(:a :b :c))))

(def f)

(println (testf f))