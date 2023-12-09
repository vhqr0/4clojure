;; Write a function which generates the <a href="http://en.wikipedia.org/wiki/Transitive_closure">transitive closure</a> of
;; a <a href="http://en.wikipedia.org/wiki/Binary_relation">binary relation</a>. The relation will be represented as a set
;; of 2 item vectors.

(defn testf [__]
  (and
   (let [divides #{[8 4] [9 3] [4 2] [27 9]}] (= (__ divides) #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]}))
   (let [more-legs #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}] (= (__ more-legs) #{["cat" "man"] ["cat" "snake"] ["man" "snake"] ["spider" "cat"] ["spider" "man"] ["spider" "snake"]}))
   (let [progeny #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}] (= (__ progeny) #{["father" "son"] ["father" "grandson"] ["uncle" "cousin"] ["son" "grandson"]}))))

(defn f [s]
  (letfn [(transitive-closure-conj [coll r]
            (into coll
                  (cons
                   r
                   (concat (map #(vector (first %1) (last r)) (filter #(= (last %1) (first r)) coll))
                           (map #(vector (first r) (last %1)) (filter #(= (first %1) (last r)) coll))))))
          (transitive-closure-into [rcoll coll]
                                   (if (empty? coll)
                                     rcoll
                                     (recur (transitive-closure-conj rcoll (first coll)) (rest coll))))]
    (transitive-closure-into #{} s)))

(println (testf f))
