;; Sets are collections of unique values.

(defn testf [__]
  (and
   (= __ (set '(:a :a :b :c :c :c :c :d :d)))
   (= __ (clojure.set/union #{:a :b :c} #{:b :c :d}))))

(def f #{:a :b :c :d})

(println (testf f))
