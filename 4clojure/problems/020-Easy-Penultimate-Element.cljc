;; Write a function which returns the second to last element from a sequence.

(defn testf [__]
  (and
   (= (__ (list 1 2 3 4 5)) 4)
   (= (__ ["a" "b" "c"]) "b")
   (= (__ [[1 2] [3 4]]) [1 2])))

;; (def f (comp last butlast))

(defn f [coll]
  (cond (empty? coll) nil
        (empty? (rest coll)) nil
        (empty? (rest (rest coll))) (first coll)
        true (recur (rest coll))))

(println (testf f))
