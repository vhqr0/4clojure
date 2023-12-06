;; Sets are collections of unique values.

(defn test [__]
  (and
   (= __ (set '(:a :a :b :c :c :c :c :d :d)))
   (= __ (clojure.set/union #{:a :b :c} #{:b :c :d}))))

(defn f [])

(println (testf f))