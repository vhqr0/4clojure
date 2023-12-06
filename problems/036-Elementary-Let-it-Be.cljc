;; Can you bind x, y, and z so that these are all true?

(defn testf [__]
  (and
   ;; (= 10 (let __ (+ x y)))
   ;; (= 4 (let __ (+ y z)))
   ;; (= 1 (let __ z))
   (= 10 (let [[x y z] __] (+ x y)))
   (= 4 (let [[x y z] __] (+ y z)))
   (= 1 (let [[x y z] __] z))
   ))

(def f [7 3 1])

(println (testf f))
