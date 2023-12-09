;; Enter a value which satisfies the following:

(defn testf [__]
  (and
   (let [x __] (and (= (class x) x) x))))

(def f)

(println (testf f))