;; Write a function which flattens any nested combination of sequential things (lists, vectors, etc.), but maintains the
;; lowest level sequential items. The result should be a sequence of sequences with only one level of nesting.

(defn testf [__]
  (and
   (= (__ [["Do"] ["Nothing"]]) [["Do"] ["Nothing"]])
   (= (__ [[[[:a :b]]] [[:c :d]] [:e :f]]) [[:a :b] [:c :d] [:e :f]])
   (= (__ '((1 2)((3 4)((((5 6))))))) '((1 2)(3 4)(5 6)))))

(def f)

(println (testf f))