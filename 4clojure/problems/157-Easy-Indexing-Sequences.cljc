;; Transform a sequence into a sequence of pairs containing the original elements along with their index.

(defn testf [__]
  (and
   (= (__ [:a :b :c]) [[:a 0] [:b 1] [:c 2]])
   (= (__ [0 1 3]) '((0 0) (1 1) (3 2)))
   (= (__ [[:foo] {:bar :baz}]) [[[:foo] 0] [{:bar :baz} 1]])))

(def f)

(println (testf f))