;; Write a function which takes a variable number of booleans. Your function should return true if some of the parameters
;; are true, but not all of the parameters are true. Otherwise your function should return false.

(defn testf [__]
  (and
   (= false (__ false false))
   (= true (__ true false))
   (= false (__ true))
   (= true (__ false true false))
   (= false (__ true true true))
   (= true (__ true true true false))))

(defn f [& bs]
  (true? (and (some true? bs) (some false? bs))))

(println (testf f))
