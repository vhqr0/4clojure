;; A mad scientist with tenure has created an experiment tracking mice in a maze. Several mazes have been randomly
;; generated, and you've been tasked with writing a program to determine the mazes in which it's possible for the mouse to
;; reach the cheesy endpoint. Write a function which accepts a maze in the form of a collection of rows, each row is a
;; string where: <ul> <li>spaces represent areas where the mouse can walk freely</li> <li>hashes (#) represent walls where
;; the mouse can not walk</li> <li>M represents the mouse's starting point</li> <li>C represents the cheese which the mouse
;; must reach</li> </ul> The mouse is not allowed to travel diagonally in the maze (only up/down/left/right), nor can he
;; escape the edge of the maze. Your function must return true iff the maze is solvable by the mouse.

(defn testf [__]
  (and
   ;; (= true (__ ["M C"]))
   ;; (= false (__ ["M # C"]))
   ;; (= true (__ ["#######" "# #" "# # #" "#M # C#" "#######"]))
   ;; (= false (__ ["########" "#M # #" "# # #" "# # # #" "# # #" "# # #" "# # # #" "# # #" "# # C#" "########"]))
   ;; (= false (__ ["M " " " " " " " " ##" " #C"]))
   ;; (= true (__ ["C######" " # " " # # " " # #M" " # "]))
   ;; (= true (__ ["C# # # #" " " "# # # # " " " " # # # #" " " "# # # #M"]))
   (= true  (__ ["M   C"]))
   (= false (__ ["M # C"]))
   (= true  (__ ["#######"
                 "#     #"
                 "#  #  #"
                 "#M # C#"
                 "#######"]))
   (= false (__ ["########"
                 "#M  #  #"
                 "#   #  #"
                 "# # #  #"
                 "#   #  #"
                 "#  #   #"
                 "#  # # #"
                 "#  #   #"
                 "#  #  C#"
                 "########"]))
   (= false (__ ["M     "
                 "      "
                 "      "
                 "      "
                 "    ##"
                 "    #C"]))
   (= true  (__ ["C######"
                 " #     "
                 " #   # "
                 " #   #M"
                 "     # "]))
   (= true  (__ ["C# # # #"
                 "        "
                 "# # # # "
                 "        "
                 " # # # #"
                 "        "
                 "# # # #M"]))))

(defn f [maze]
  (letfn [(maze-space-conj [coll [c i j]]
            (let [e (if (= c \space) #{[i j]} #{c [i j]})
                  c1 (some #(if (%1 [(dec i) j]) %1) coll)
                  c2 (some #(if (%1 [i (dec j)]) %1) coll)]
              (cond (and c1 c2 (= c1 c2)) (conj (disj coll c1) (into c1 e))
                    (and c1 c2 (not= c1 c2)) (conj (disj coll c1 c2) (into (into c1 c2) e))
                    c1 (conj (disj coll c1) (into c1 e))
                    c2 (conj (disj coll c2) (into c2 e))
                    true (conj coll e))))]
    (true?
     (->> (for [i (range (count maze)) j (range (count (first maze)))] [(get (get maze i) j) i j])
          (filter #(not= (first %1) \#))
          (reduce maze-space-conj #{})
          (some #(and (contains? %1 \M) (contains? %1 \C)))))))

(println (testf f))
