;; Given a graph, determine whether the graph is connected. A connected graph is such that a path exists between any two
;; given nodes.<br/><br/>-Your function must return true if the graph is connected and false otherwise.<br/><br/>-You will
;; be given a set of tuples representing the edges of a graph. Each member of a tuple being a vertex/node in the
;; graph.<br/><br/>-Each edge is undirected (can be traversed either direction).

(defn testf [__]
  (and
   (= true (__ #{[:a :a]}))
   (= true (__ #{[:a :b]}))
   (= false (__ #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4]}))
   (= true (__ #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4] [3 4]}))
   (= false (__ #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e]}))
   (= true (__ #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e] [:x :a]}))))

(defn f [coll]
  (letfn [(graph-conj [gs [p1 p2]]
            (let [g1 (some #(if (%1 p1) %1) gs)
                  g2 (some #(if (%1 p2) %1) gs)]
              (cond (and g1 g2 (= g1 g2)) gs
                    (and g1 g2 (not= g1 g2)) (conj (disj gs g1 g2) (into g1 g2))
                    g1 (conj (disj gs g1) (conj g1 p2))
                    g2 (conj (disj gs g2) (conj g2 p1))
                    true (conj gs (hash-set p1 p2)))))]
    (= (count (reduce graph-conj #{} coll)) 1)))

(println (testf f))
