;; Write a function which generates the <a href="http://en.wikipedia.org/wiki/Power_set">power set</a> of a given set. The
;; power set of a set x is the set of all subsets of x, including the empty set and x itself.

(defn testf [__]
  (and
   (= (__ #{1 :a}) #{#{1 :a} #{:a} #{} #{1}})
   (= (__ #{}) #{#{}})
   (= (__ #{1 2 3}) #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})
   (= (count (__ (into #{} (range 10)))) 1024)))

(defn f [s]
  (letfn [(combination [coll n]
            (cond (= n 0) (list #{})
                  (empty? coll) ()
                  true (lazy-seq (concat (map #(conj %1 (first coll)) (combination (rest coll) (dec n)))
                                         (combination (rest coll) n)))))
          (combination-all [coll n]
            (if (= n 0)
              (list #{})
              (lazy-seq (concat (combination coll n) (combination-all coll (dec n))))))]
    (into #{} (combination-all s (count s)))))

(println (testf f))
