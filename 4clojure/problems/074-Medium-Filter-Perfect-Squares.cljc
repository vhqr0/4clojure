;; Given a string of comma separated integers, write a function which returns a new comma separated string that only
;; contains the numbers which are perfect squares.

(defn testf [__]
  (and
   (= (__ "4,5,6,7,8,9") "4,9")
   (= (__ "15,16,25,36,37") "16,25,36")))

(defn f [s]
  (->> (clojure.string/split s #",")
       (map clojure.edn/read-string)
       (filter #(<= (rem (clojure.math/sqrt %1) 1.0) 0.0000001))
       (interpose ",")
       (apply str)))

(println (testf f))
