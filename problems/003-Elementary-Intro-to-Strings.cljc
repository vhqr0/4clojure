;; Clojure strings are Java strings. This means that you can use any of the Java string methods on Clojure strings.

(defn testf [__]
  (and
   (= __ (.toUpperCase "hello world"))))

(def f)

(println (testf f))