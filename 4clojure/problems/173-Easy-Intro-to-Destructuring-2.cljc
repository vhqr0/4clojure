;; Sequential destructuring allows you to bind symbols to parts of sequential things (vectors, lists, seqs, etc.): <a
;; href="http://clojure.org/special_forms#Special Forms--(let [bindings* ] exprs*)">(let [bindings* ] exprs*)</a> Complete
;; the bindings so all let-parts evaluate to 3.

(defn testf [__]
  (and
   (= 3 (let [[__] [+ (range 3)]] (apply __)) (let [[[__] b] [[+ 1] 2]] (__ b)) (let [[__] [inc 2]] (__)))))

(def f)

(println (testf f))