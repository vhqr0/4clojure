;; Write a function which flattens a sequence.

;; restricted: flatten 

(defn test [__]
  (and
   (= (__ '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
   (= (__ ["a" ["b"] "c"]) '("a" "b" "c"))
   (= (__ '((((:a))))) '(:a))))

(defn f [])

(println (testf f))