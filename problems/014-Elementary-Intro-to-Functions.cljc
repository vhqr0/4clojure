;; Clojure has many different ways to create functions.

(defn testf [__]
  (and
   (= __ ((fn add-five [x] (+ x 5)) 3))
   (= __ ((fn [x] (+ x 5)) 3))
   (= __ (#(+ % 5) 3))
   (= __ ((partial + 5) 3))))

(def f 8)

(println (testf f))
