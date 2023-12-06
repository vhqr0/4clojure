;; Create a function of no arguments which returns a string that is an <i>exact</i> copy of the function itself. <br /><br
;; /> Hint: read <a href="http://en.wikipedia.org/wiki/Quine_(computing)">this</a> if you get stuck (this question is
;; harder than it first appears); but it's worth the effort to solve it independently if you can! <br /><br /> Fun fact:
;; Gus is the name of the <a href="http://i.imgur.com/FBd8z.png">4Clojure dragon</a>.

(defn testf [__]
  (and
   (= (str '__) (__))))

(def f)

(println (testf f))