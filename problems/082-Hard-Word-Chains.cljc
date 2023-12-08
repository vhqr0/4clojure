;; A word chain consists of a set of words ordered so that each word differs by only one letter from the words directly
;; before and after it. The one letter difference can be either an insertion, a deletion, or a substitution. Here is an
;; example word chain:<br/><br/>cat -> cot -> coat -> oat -> hat -> hot -> hog -> dog<br/><br/>Write a function which takes
;; a sequence of words, and returns true if they can be arranged into one continous word chain, and false if they cannot.

(defn testf [__]
  (and
   (= true (__ #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}))
   (= false (__ #{"cot" "hot" "bat" "fat"}))
   (= false (__ #{"to" "top" "stop" "tops" "toss"}))
   (= true (__ #{"spout" "do" "pot" "pout" "spot" "dot"}))
   (= true (__ #{"share" "hares" "shares" "hare" "are"}))
   (= false (__ #{"share" "hares" "hare" "are"}))))

(defn f [coll]
  (letfn [(word-chain-change-step? [x y]
            (and
             (= (count x) (count y))
             (= (count (filter not (map = x y))) 1)))
          (word-chain-insert-step? [x y]
            (and
             (= (inc (count x)) (count y))
             (some #(= (seq x) (concat (take %1 y) (drop (inc %1) y))) (range (count y)))))
          (word-chain-step? [x y]
            (or (word-chain-change-step? x y)
                (word-chain-insert-step? x y)
                (word-chain-insert-step? y x)))
          (filter-word-chain-step [x coll]
            (cond (empty? coll) ()
                  (not (word-chain-step? x (first coll))) (recur x (rest coll))
                  true (lazy-seq (cons (first coll) (filter-word-chain-step x (rest coll))))))
          (word-chain? [x coll]
            (if (empty? coll)
              true
              (true? (some #(word-chain? %1 (disj coll %1)) (filter-word-chain-step x coll)))))]
    (true? (some #(word-chain? %1 (disj coll %1)) coll))))

(println (testf f))
