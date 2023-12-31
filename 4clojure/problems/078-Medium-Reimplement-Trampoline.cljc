;; Reimplement the function described in <a href="76"> "Intro to Trampoline"</a>.

;; restricted: trampoline 

(defn testf [__]
  (and
   (= (letfn [(triple [x] #(sub-two (* 3 x))) (sub-two [x] #(stop?(- x 2))) (stop? [x] (if (> x 50) x #(triple x)))] (__ triple 2)) 82)
   (= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x)))) (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))] (map (partial __ my-even?) (range 6))) [true false true false true false])))

(defn f [g & args]
  (if (fn? g) (recur (apply g args) nil) g))

(println (testf f))
