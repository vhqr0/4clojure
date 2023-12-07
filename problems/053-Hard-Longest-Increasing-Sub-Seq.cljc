;; Given a vector of integers, find the longest consecutive sub-sequence of increasing numbers. If two sub-sequences have
;; the same length, use the one that occurs first. An increasing sub-sequence must have a length of 2 or greater to
;; qualify.

(defn testf [__]
  (and
   (= (__ [1 0 1 2 3 0 4 5]) [0 1 2 3])
   (= (__ [5 6 1 3 2 7]) [5 6])
   (= (__ [2 3 3 4 5]) [3 4 5])
   (= (__ [7 6 5 4]) [])))

(defn f [coll]
  (letfn [(take-consecutive [rcoll coll]
            (if (or (empty? coll) (not= (first coll) (inc (last rcoll))))
              [rcoll coll]
              (recur (conj rcoll (first coll)) (rest coll))))]
    (loop [rcoll [] rcnt 1 coll coll]
      (if (empty? coll)
        rcoll
        (let [[nrcoll ncoll] (take-consecutive [(first coll)] (rest coll))
              nrcnt (count nrcoll)]
          (if (> nrcnt rcnt)
            (recur nrcoll nrcnt ncoll)
            (recur rcoll rcnt ncoll)))))))

(println (testf f))
