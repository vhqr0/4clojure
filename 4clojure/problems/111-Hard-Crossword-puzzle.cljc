;; Write a function that takes a string and a partially-filled crossword puzzle board, and determines if the input string
;; can be legally placed onto the board. </br></br> The crossword puzzle board consists of a collection of partially-filled
;; rows. Empty spaces are denoted with an underscore (_), unusable spaces are denoted with a hash symbol (#), and pre-
;; filled spaces have a character in place; the whitespace characters are for legibility and should be ignored. </br></br>
;; For a word to be legally placed on the board: </br> - It may use empty spaces (underscores) </br> - It may use but must
;; not conflict with any pre-filled characters. </br> - It must not use any unusable spaces (hashes). </br> - There must be
;; no empty spaces (underscores) or extra characters before or after the word (the word may be bound by unusable spaces
;; though). </br> - Characters are not case-sensitive. </br> - Words may be placed vertically (proceeding top-down only),
;; or horizontally (proceeding left-right only).

(defn testf [__]
  (and
   (= true (__ "the" ["_ # _ _ e"]))
   (= false (__ "the" ["c _ _ _"
                       "d _ # e"
                       "r y _ _"]))
   (= true (__ "joy" ["c _ _ _"
                      "d _ # e"
                      "r y _ _"]))
   (= false (__ "joy" ["c o n j"
                       "_ _ y _"
                       "r _ _ #"]))
   (= true (__ "clojure" ["_ _ _ # j o y"
                          "_ _ o _ _ _ _"
                          "_ _ f _ # _ _"]))))

(defn f [word board]
  (letfn [(fill? [word coll]
            (cond (empty? word) (or (empty? coll) (= (first coll) \#))
                  (empty? coll) false
                  (or (= (first coll) \_) (= (first coll) (first word))) (recur (rest word) (rest coll))
                  true false))
          (fill-row? [word coll]
            (cond (empty? coll) false
                  (and (= (first coll) \#) (fill? word (rest coll))) true ; notice: cons a sentinel \#
                  true (recur word (rest coll))))]
    (let [board (map (fn [s] (filter #(not= %1 \space) s)) board)
          rows (concat board (apply map vector board))]
      (->> rows
           (map #(cons \# %1))
           (some #(fill-row? word %1))
           (true?)))))

(println (testf f))
