;; Can you bind x, y, and z so that these are all true?

(defn testf [__]
  (and
   (= 10 (let __ (+ x y)))
   (= 4 (let __ (+ y z)))
   (= 1 (let __ z))))

(def f)

(println (testf f))