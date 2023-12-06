;; Clojure has many different ways to create functions.

(defn test [__]
  (and
   (= __ ((fn add-five [x] (+ x 5)) 3))
   (= __ ((fn [x] (+ x 5)) 3))
   (= __ (#(+ % 5) 3))
   (= __ ((partial + 5) 3))))

(defn f [])

(println (testf f))