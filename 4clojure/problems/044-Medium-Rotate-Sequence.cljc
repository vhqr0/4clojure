;; Write a function which can rotate a sequence in either direction.

(defn testf [__]
  (and
   (= (__ 2 [1 2 3 4 5]) '(3 4 5 1 2))
   (= (__ -2 [1 2 3 4 5]) '(4 5 1 2 3))
   (= (__ 6 [1 2 3 4 5]) '(2 3 4 5 1))
   (= (__ 1 '(:a :b :c)) '(:b :c :a))
   (= (__ -4 '(:a :b :c)) '(:c :a :b))))

(defn f [n coll]
  (let [cnt (count coll)
        n (if (>= n 0) (rem n cnt) (+ cnt (rem n cnt)))]
    (concat (drop n coll) (take n coll))))

(println (testf f))
