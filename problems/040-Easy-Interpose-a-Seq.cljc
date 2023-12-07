;; Write a function which separates the items of a sequence by an arbitrary value.

;; restricted: interpose 

(defn testf [__]
  (and
   (= (__ 0 [1 2 3]) [1 0 2 0 3])
   (= (apply str (__ ", " ["one" "two" "three"])) "one, two, three")
   (= (__ :z [:a :b :c :d]) [:a :z :b :z :c :z :d])))

(defn f [x coll]
  (cond (empty? coll) ()
        (empty? (rest coll)) (list (first coll))
        true (lazy-seq (cons (first coll) (cons x (f x (rest coll)))))))

(println (testf f))
