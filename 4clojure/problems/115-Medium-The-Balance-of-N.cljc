;; A balanced number is one whose component digits have the same sum on the left and right halves of the number. Write a
;; function which accepts an integer n, and returns true iff n is balanced.

(defn testf [__]
  (and
   (= true (__ 11))
   (= true (__ 121))
   (= false (__ 123))
   (= true (__ 0))
   (= false (__ 88099))
   (= true (__ 89098))
   (= true (__ 89089))
   (= (take 20 (filter __ (range))) [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101])))

(defn f [n]
  (letfn [(iterate-digits [n base]
            (if (zero? n)
              ()
              (lazy-seq (cons (rem n base) (iterate-digits (quot n base) base)))))
          (balanced-digits? [coll]
            (let [cnt (count coll)]
              (cond (or (= cnt 0) (= cnt 1)) true
                    (or (= cnt 2) (= cnt 3)) (= (first coll) (last coll))
                    true (recur (concat (list (+ (first coll) (first (rest coll))))
                                        (rest (rest (butlast (butlast coll))))
                                        (list (+ (last coll) (last (butlast coll)))))))))]
    (balanced-digits? (iterate-digits n 10))))

(println (testf f))
