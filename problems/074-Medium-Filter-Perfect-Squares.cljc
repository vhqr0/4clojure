;; Given a string of comma separated integers, write a function which returns a new comma separated string that only
;; contains the numbers which are perfect squares.

(defn testf [__]
  (and
   (= (__ "4,5,6,7,8,9") "4,9")
   (= (__ "15,16,25,36,37") "16,25,36")))

(def f)

(println (testf f))