;; Regex patterns are supported with a special reader macro.

(defn test [__]
  (and
   (= __ (apply str (re-seq #"[A-Z]+" "bA1B3Ce ")))))

(defn f [])

(println (testf f))