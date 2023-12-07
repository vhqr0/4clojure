;; Write a function which will split a sequence into two parts.

;; restricted: split-at 

(defn testf [__]
  (and
   (= (__ 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])
   (= (__ 1 [:a :b :c :d]) [[:a] [:b :c :d]])
   (= (__ 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]])))

(defn f [n coll]
  [(take n coll) (drop n coll)])

(println (testf f))
