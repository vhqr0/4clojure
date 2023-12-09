;; This is the inverse of <a href='92'>Problem 92</a>, but much easier. Given an integer smaller than 4000, return the
;; corresponding roman numeral in uppercase, adhering to the <a
;; href='http://www.numericana.com/answer/roman.htm#valid'>subtractive principle</a>.

(defn testf [__]
  (and
   (= "I" (__ 1))
   (= "XXX" (__ 30))
   (= "IV" (__ 4))
   (= "CXL" (__ 140))
   (= "DCCCXXVII" (__ 827))
   (= "MMMCMXCIX" (__ 3999))
   (= "XLVIII" (__ 48))))

(defn f [n]
  (letfn [(roman-char-iterate [n]
            (cond (>= n 1000) (lazy-seq (concat '(   \M) (roman-char-iterate (- n 1000))))
                  (>= n 900)  (lazy-seq (concat '(\C \M) (roman-char-iterate (- n 900))))
                  (>= n 500)  (lazy-seq (concat '(   \D) (roman-char-iterate (- n 500))))
                  (>= n 400)  (lazy-seq (concat '(\C \D) (roman-char-iterate (- n 400))))
                  (>= n 100)  (lazy-seq (concat '(   \C) (roman-char-iterate (- n 100))))
                  (>= n 90)   (lazy-seq (concat '(\X \C) (roman-char-iterate (- n 90))))
                  (>= n 50)   (lazy-seq (concat '(   \L) (roman-char-iterate (- n 50))))
                  (>= n 40)   (lazy-seq (concat '(\X \L) (roman-char-iterate (- n 40))))
                  (>= n 10)   (lazy-seq (concat '(   \X) (roman-char-iterate (- n 10))))
                  (>= n 9)    (lazy-seq (concat '(\I \X) (roman-char-iterate (- n 9))))
                  (>= n 5)    (lazy-seq (concat '(   \V) (roman-char-iterate (- n 5))))
                  (>= n 4)    (lazy-seq (concat '(\I \V) (roman-char-iterate (- n 4))))
                  (>= n 1)    (lazy-seq (concat '(   \I) (roman-char-iterate (dec n))))))]
    (apply str (roman-char-iterate n))))

(println (testf f))
