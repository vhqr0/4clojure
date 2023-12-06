;; Enter a value which satisfies the following:

(defn test [__]
  (and
   (let [x __] (and (= (class x) x) x))))

(defn f [])

(println (testf f))