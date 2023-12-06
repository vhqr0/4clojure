;; Write a function which separates the items of a sequence by an arbitrary value.

;; restricted: interpose 

(defn testf [__]
  (and
   (= (__ 0 [1 2 3]) [1 0 2 0 3])
   (= (apply str (__ ", " ["one" "two" "three"])) "one, two, three")
   (= (__ :z [:a :b :c :d]) [:a :z :b :z :c :z :d])))

(defn f [x coll] ((fn [rcoll x coll] (if (empty? coll) rcoll (recur (conj (conj rcoll x) (first coll)) x (rest coll)))) [(first coll)] x (rest coll)))

(println (testf f))
