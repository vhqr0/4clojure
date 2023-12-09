;; Regex patterns are supported with a special reader macro.

(defn testf [__]
  (and
   (= __ (apply str (re-seq #"[A-Z]+" "bA1B3Ce ")))))

(def f "ABC")

(println (testf f))
