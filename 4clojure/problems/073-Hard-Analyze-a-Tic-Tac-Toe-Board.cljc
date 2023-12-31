;; A <a href="http://en.wikipedia.org/wiki/Tic-tac-toe">tic-tac-toe</a> board is represented by a two dimensional vector. X
;; is represented by :x, O is represented by :o, and empty is represented by :e. A player wins by placing three Xs or three
;; Os in a horizontal, vertical, or diagonal row. Write a function which analyzes a tic-tac-toe board and returns :x if X
;; has won, :o if O has won, and nil if neither player has won.

(defn testf [__]
  (and
   (= nil (__ [[:e :e :e] [:e :e :e] [:e :e :e]]))
   (= :x (__ [[:x :e :o] [:x :e :e] [:x :e :o]]))
   (= :o (__ [[:e :x :e] [:o :o :o] [:x :e :x]]))
   (= nil (__ [[:x :e :o] [:x :x :e] [:o :x :o]]))
   (= :x (__ [[:x :e :e] [:o :x :e] [:o :e :x]]))
   (= :o (__ [[:x :e :o] [:x :o :e] [:o :e :x]]))
   (= nil (__ [[:x :o :x] [:x :o :x] [:o :x :o]]))))

(defn f [ttt]
  (letfn [(ttt-winner? [ttt p]
            (or (= p ((ttt 0) 0) ((ttt 0) 1) ((ttt 0) 2))
                (= p ((ttt 1) 0) ((ttt 1) 1) ((ttt 1) 2))
                (= p ((ttt 2) 0) ((ttt 2) 1) ((ttt 2) 2))
                (= p ((ttt 0) 0) ((ttt 1) 0) ((ttt 2) 0))
                (= p ((ttt 0) 1) ((ttt 1) 1) ((ttt 2) 1))
                (= p ((ttt 0) 2) ((ttt 1) 2) ((ttt 2) 2))
                (= p ((ttt 0) 0) ((ttt 1) 1) ((ttt 2) 2))
                (= p ((ttt 0) 2) ((ttt 1) 1) ((ttt 2) 0))))]
    (cond (ttt-winner? ttt :x) :x
          (ttt-winner? ttt :o) :o)))

(println (testf f))
