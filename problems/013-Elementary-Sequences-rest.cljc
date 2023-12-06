;; The rest function will return all the items of a sequence except the first.

(defn testf [__]
  (and
   (= __ (rest [10 20 30 40]))))

(def f)

(println (testf f))