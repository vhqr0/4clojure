;; Given an input sequence of keywords and numbers, create a map such that each key in the map is a keyword, and the value
;; is a sequence of all the numbers (if any) between it and the next keyword in the sequence.

(defn test [__]
  (and
   (= {} (__ []))
   (= {:a [1]} (__ [:a 1]))
   (= {:a [1], :b [2]} (__ [:a 1, :b 2]))
   (= {:a [1 2 3], :b [], :c [4]} (__ [:a 1 2 3 :b :c 4]))))

(defn f [])

(println (testf f))