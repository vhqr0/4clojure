;; Starting with a graph you must write a function that returns true if it is possible to make a tour of the graph in which
;; every edge is visited exactly once.<br/><br/>The graph is represented by a vector of tuples, where each tuple represents
;; a single edge.<br/><br/>The rules are:<br/><br/>- You can start at any node.<br/>- You must visit each edge exactly
;; once.</br>- All edges are undirected.

(defn testf [__]
  (and
   (= true (__ [[:a :b]]))
   (= false (__ [[:a :a] [:b :b]]))
   (= false (__ [[:a :b] [:a :b] [:a :c] [:c :a] [:a :d] [:b :d] [:c :d]]))
   (= true (__ [[1 2] [2 3] [3 4] [4 1]]))
   (= true (__ [[:a :b] [:a :c] [:c :b] [:a :e] [:b :e] [:a :d] [:b :d] [:c :e] [:d :e] [:c :f] [:d :f]]))
   (= false (__ [[1 2] [2 3] [2 4] [2 5]]))))

(defn f [coll]
  (letfn [(graph-tour? [x coll]
            (if (empty? coll)
              true
              (true? (some (fn [[i [y z]]]
                             (cond (= y x) (graph-tour? z (concat (take i coll) (drop (inc i) coll)))
                                   (= z x) (graph-tour? y (concat (take i coll) (drop (inc i) coll)))))
                           (map-indexed vector coll)))))]
    (true? (some #(graph-tour? %1 coll) (reduce into #{} coll)))))

(println (testf f))
