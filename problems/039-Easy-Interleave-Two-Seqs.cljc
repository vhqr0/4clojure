;; Write a function which takes two sequences and returns the first item from each, then the second item from each, then
;; the third, etc.

;; restricted: interleave 

(defn testf [__]
  (and
   (= (__ [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))
   (= (__ [1 2] [3 4 5 6]) '(1 3 2 4))
   (= (__ [1 2 3 4] [5]) [1 5])
   (= (__ [30 20] [25 15]) [30 25 20 15])))

(defn f [coll1 coll2] ((fn [rcoll coll1 coll2] (if (or (empty? coll1) (empty? coll2)) rcoll (recur (conj (conj rcoll (first coll1)) (first coll2)) (rest coll1) (rest coll2)))) [] coll1 coll2))

(println (testf f))
