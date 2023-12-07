;; Write a function which takes a string and returns a new string containing only the capital letters.

(defn testf [__]
  (and
   (= (__ "HeLlO, WoRlD!") "HLOWRD")
   (empty? (__ "nothing"))
   (= (__ "$#A(*&987Zf") "AZ")))

(defn f [s] (apply str (re-seq #"[A-Z]" s)))

(println (testf f))
