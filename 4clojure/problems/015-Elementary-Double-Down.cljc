;; Write a function which doubles a number.

(defn testf [__]
  (and
   (= (__ 2) 4)
   (= (__ 3) 6)
   (= (__ 11) 22)
   (= (__ 7) 14)))

(def f #(* 2 %))

(println (testf f))
