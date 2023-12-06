;; Clojure strings are Java strings. This means that you can use any of the Java string methods on Clojure strings.

(defn test [__]
  (and
   (= __ (.toUpperCase "hello world"))))

(defn f [])

(println (testf f))