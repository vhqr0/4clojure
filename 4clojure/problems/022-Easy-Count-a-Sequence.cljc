;; Write a function which returns the total number of elements in a sequence.

;; restricted: count 

(defn testf [__]
  (and
   (= (__ '(1 2 3 3 1)) 5)
   (= (__ "Hello World") 11)
   (= (__ [[1 2] [3 4] [5 6]]) 3)
   (= (__ '(13)) 1)
   (= (__ '(:a :b :c)) 3)))

(def f (partial reduce (fn [acc x] (inc acc)) 0))

(println (testf f))
