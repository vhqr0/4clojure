;; Write a function which returns the Nth element from a sequence.

;; restricted: nth 

(defn test [__]
  (and
   (= (__ '(4 5 6 7) 2) 6)
   (= (__ [:a :b :c] 0) :a)
   (= (__ [1 2 3 4] 1) 2)
   (= (__ '([1 2] [3 4] [5 6]) 2) [5 6])))

(defn f [])

(println (testf f))