;; Write a function which will split a sequence into two parts.

;; restricted: split-at 

(defn testf [__]
  (and
   (= (__ 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])
   (= (__ 1 [:a :b :c :d]) [[:a] [:b :c :d]])
   (= (__ 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]])))

(defn f [n coll] ((fn [rcoll coll n] (if (= n 0) [rcoll coll] (recur (conj rcoll (first coll)) (rest coll) (dec n)))) [] coll n))

(println (testf f))
