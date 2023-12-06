;; Write a function which flattens a sequence.

;; restricted: flatten 

(defn testf [__]
  (and
   (= (__ '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
   (= (__ ["a" ["b"] "c"]) '("a" "b" "c"))
   (= (__ '((((:a))))) '(:a))))

(defn f [coll] ((fn [rcoll coll colls]
                  (cond (and (empty? coll) (empty? colls)) rcoll
                        (empty? coll) (recur rcoll colls '())
                        (or (seq? (first coll)) (vector? (first coll))) (recur rcoll (first coll) (concat (rest coll) colls))
                        true (recur (conj rcoll (first coll)) (rest coll) colls)))
                [] coll '()))

(println (testf f))
