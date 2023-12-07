;; Write a function which takes a sequence consisting of items with different types and splits them up into a set of
;; homogeneous sub-sequences. The internal order of each sub-sequence should be maintained, but the sub-sequences
;; themselves can be returned in any order (this is why 'set' is used in the test cases).

(defn testf [__]
  (and
   (= (set (__ [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})
   (= (set (__ [:a "foo" "bar" :b])) #{[:a :b] ["foo" "bar"]})
   (= (set (__ [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]})))


(defn f [coll] ((fn [rcolls coll]
                  (if (empty? coll)
                    rcolls
                    (let [[rcoll fcoll] ((fn [coll]
                                           ((fn [rcoll fcoll coll t] (cond (empty? coll) [rcoll fcoll]
                                                                           (= (type (first coll)) t) (recur (conj rcoll (first coll)) fcoll (rest coll) t)
                                                                           true (recur rcoll (conj fcoll (first coll)) (rest coll) t)))
                                            [(first coll)] [] (rest coll) (type (first coll))))
                                         coll)]
                      (recur (conj rcolls rcoll) fcoll))))
                [] coll))

(println (testf f))
