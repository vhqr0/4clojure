;; Write a function which flattens a sequence.

;; restricted: flatten 

(defn testf [__]
  (and
   (= (__ '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
   (= (__ ["a" ["b"] "c"]) '("a" "b" "c"))
   (= (__ '((((:a))))) '(:a))))

(defn f [coll]
  (cond (empty? coll) ()
        (coll? (first coll)) (lazy-seq (f (concat (first coll) (rest coll))))
        true (lazy-seq (cons (first coll) (f (rest coll))))))

(println (testf f))
