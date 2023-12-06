;; Write a function which returns the last element in a sequence.

;; restricted: last 

(defn testf [__]
  (and
   (= (__ [1 2 3 4 5]) 5)
   (= (__ '(5 4 3)) 3)
   (= (__ ["b" "c" "d"]) "d")))

(defn f [coll] ((fn [coll x] (if (empty? coll) x (recur (rest coll) (first coll)))) coll nil))

(println (testf f))
