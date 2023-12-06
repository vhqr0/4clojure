;; Write a function which takes a variable number of parameters and returns the maximum value.

;; restricted: max max-key 

(defn testf [__]
  (and
   (= (__ 1 8 3 4) 8)
   (= (__ 30 20) 30)
   (= (__ 45 67 11) 67)))

(defn f [& coll] ((fn [coll x] (if (empty? coll) x (recur (rest coll) (if (> (first coll) x) (first coll) x)))) coll 0))

(println (testf f))
