;; Roman numerals are easy to recognize, but not everyone knows all the rules necessary to work with them. Write a function
;; to parse a Roman-numeral string and return the number it represents. <br /><br /> You can assume that the input will be
;; well-formed, in upper-case, and follow the <a
;; href="http://en.wikipedia.org/wiki/Roman_numerals#Subtractive_principle">subtractive principle</a>. You don't need to
;; handle any numbers greater than MMMCMXCIX (3999), the largest number representable with ordinary letters.

(defn testf [__]
  (and
   (= 14 (__ "XIV"))
   (= 827 (__ "DCCCXXVII"))
   (= 3999 (__ "MMMCMXCIX"))
   (= 48 (__ "XLVIII"))))

(defn f [s]
  (let [roman-val {\M 1000
                   \D  500
                   \C  100
                   \L   50
                   \X   10
                   \V    5
                   \I    1}]
    (letfn [(roman-int-val [roman]
              (if (coll? roman)
                (- (roman-val (last roman)) (roman-val (first roman)))
                (roman-val roman)))
            (roman-iterate [coll]
              (cond (empty? coll) ()
                    (or (empty? (rest coll)) (>= (roman-val (first coll)) (roman-val (nth coll 1)))) (lazy-seq (cons (first coll) (roman-iterate (rest coll))))
                    true (lazy-seq (cons (take 2 coll) (roman-iterate (drop 2 coll))))))]
      (->> (roman-iterate s)
           (map roman-int-val)
           (apply +)))))

(println (testf f))
