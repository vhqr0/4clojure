;; Lists can be constructed with either a function or a quoted form.

(defn testf [__]
  (and
   ;; (= (list __) '(:a :b :c))
   (= __ '(:a :b :c))))

(def f (list :a :b :c))

(println (testf f))
