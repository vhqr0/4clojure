;; Given a function f and a sequence s, write a function which returns a map. The keys should be the values of f applied to
;; each item in s. The value at each key should be a vector of corresponding items in the order they appear in s.

;; restricted: group-by 

(defn testf [__]
  (and
   (= (__ #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})
   (= (__ #(apply / %) [[1 2] [2 4] [4 6] [3 6]]) {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})
   (= (__ count [[1] [1 2] [3] [1 2 3] [2 3]]) {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})))

(defn f [g coll]
  (letfn [(split-group [group rcoll fcoll coll]
            (cond (empty? coll) [group rcoll fcoll]
                  (= (g (first coll)) group) (recur group (conj rcoll (first coll)) fcoll (rest coll))
                  true (recur group rcoll (conj fcoll (first coll)) (rest coll))))]
    (loop [rcolls [] coll coll]
      (if (empty? coll)
        (into {} rcolls)
        (let [[group rcoll fcoll] (split-group (g (first coll)) [(first coll)] [] (rest coll))]
          (recur (conj rcolls [group rcoll]) fcoll))))))

(println (testf f))
