;; Write a function which drops every Nth item from a sequence.

(defn testf [__]
  (and
   (= (__ [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])
   (= (__ [:a :b :c :d :e :f] 2) [:a :c :e])
   (= (__ [1 2 3 4 5 6] 4) [1 2 3 5 6])))

(defn f [coll n] ((fn [rcoll coll n i] (cond (empty? coll) rcoll (= i n) (recur rcoll (rest coll) n 1) true (recur (conj rcoll (first coll)) (rest coll) n (inc i)))) [] coll n 1))

(println (testf f))
