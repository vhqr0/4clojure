;; Write a higher-order function which flips the order of the arguments of an input function.

(defn testf [__]
  (and
   (= 3 ((__ nth) 2 [1 2 3 4 5]))
   (= true ((__ >) 7 8))
   (= 4 ((__ quot) 2 8))
   (= [1 2 3] ((__ take) [1 2 3 4 5] 3))))

(defn f [rf] (fn [& args] (apply rf (reverse args))))

(println (testf f))
