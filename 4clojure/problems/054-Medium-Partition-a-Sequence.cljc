;; Write a function which returns a sequence of lists of x items each. Lists of less than x items should not be returned.

;; restricted: partition partition-all 

(defn testf [__]
  (and
   (= (__ 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))
   (= (__ 2 (range 8)) '((0 1) (2 3) (4 5) (6 7)))
   (= (__ 3 (range 8)) '((0 1 2) (3 4 5)))))

(defn f [n coll]
  (let [x (take n coll)]
    (if (< (count x) n)
      ()
      (lazy-seq
       (cons x (f n (drop n coll)))))))

(println (testf f))
