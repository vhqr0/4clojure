;; Vectors can be constructed several ways. You can compare them with lists. <br/><br/><b>Note</b>: the brackets []
;; surrounding the blanks __ are part of the test case.

(defn testf [__]
  (and
   ;; (= [__] (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))
   (= __ (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))))

(def f [:a :b :c])

(println (testf f))
