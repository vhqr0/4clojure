;; Write a function which returns a sequence of lists of x items each. Lists of less than x items should not be returned.

;; restricted: partition partition-all 

(defn test [__]
  (and
   (= (__ 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))
   (= (__ 2 (range 8)) '((0 1) (2 3) (4 5) (6 7)))
   (= (__ 3 (range 8)) '((0 1 2) (3 4 5)))))

(defn f [])

(println (testf f))