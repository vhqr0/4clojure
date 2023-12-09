;; <p>Given any number of sequences, each sorted from smallest to largest, find the smallest single number which appears in
;; all of the sequences. The sequences may be infinite, so be careful to search lazily.</p>

(defn testf [__]
  (and
   (= 3 (__ [3 4 5]))
   (= 4 (__ [1 2 3 4 5 6 7] [0.5 3/2 4 19]))
   (= 7 (__ (range) (range 0 100 7/6) [2 3 5 7 11 13]))
   (= 64 (__ (map #(* % % %) (range)) ;; perfect cubes
             (filter #(zero? (bit-and % (dec %))) (range)) ;; powers of 2
             (iterate inc 20) ;; at least as large as 20
             ))))

(defn f [& colls]
  (cond (not-every? (complement empty?) colls) nil
        (every? #(= (first %1) (first (first colls))) colls) (first (first colls))
        true (let [i (first (apply min-key #(first (last %1)) (map-indexed vector colls)))]
               (recur (concat (take i colls) (list (rest (nth colls i))) (drop (inc i) colls))))))

(println (testf f))
