;; Create a function of no arguments which returns a string that is an <i>exact</i> copy of the function itself. <br /><br
;; /> Hint: read <a href="http://en.wikipedia.org/wiki/Quine_(computing)">this</a> if you get stuck (this question is
;; harder than it first appears); but it's worth the effort to solve it independently if you can! <br /><br /> Fun fact:
;; Gus is the name of the <a href="http://i.imgur.com/FBd8z.png">4Clojure dragon</a>.

(defn testf [__]
  (and
   ;; (= (str '__) (__))
   (= __ (eval __))))

;; (def i '(fn [x] x))
;; (list i (list 'quote i))
;; => '((fn [x] x) (quote (fn [x] x)))
;; (eval (list i (list 'quote i)))
;; => '(fn [x] x)

(def f '(let [g '(fn [x] (list 'let ['g (list 'quote x)] '(eval (list g (list 'quote g)))))]
          (eval (list g (list 'quote g)))))

(println (testf f))
