;; Write a function which reverses a sequence.

;; restricted: reverse rseq 

(defn test [__]
  (and
   (= (__ [1 2 3 4 5]) [5 4 3 2 1])
   (= (__ (sorted-set 5 7 2 7)) '(7 5 2))
   (= (__ [[1 2][3 4][5 6]]) [[5 6][3 4][1 2]])))

(defn f [])

(println (testf f))