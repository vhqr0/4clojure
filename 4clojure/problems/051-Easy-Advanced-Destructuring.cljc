;; Here is an example of some more sophisticated destructuring.

(defn testf [__]
  (and
   (= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] __] [a b c d]))))

(def f [1 2 3 4 5])

(println (testf f))
