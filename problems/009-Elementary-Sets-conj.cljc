;; When operating on a set, the conj function returns a new set with one or more keys "added".

(defn testf [__]
  (and
   (= #{1 2 3 4} (conj #{1 4 3} __))))

(def f 2)

(println (testf f))
