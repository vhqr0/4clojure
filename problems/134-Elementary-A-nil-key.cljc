;; Write a function which, given a key and map, returns true <a href="http://en.wikipedia.org/wiki/If_and_only_if">iff</a>
;; the map contains an entry with that key and its value is nil.

(defn test [__]
  (and
   (true? (__ :a {:a nil :b 2}))
   (false? (__ :b {:a nil :b 2}))
   (false? (__ :c {:a nil :b 2}))))

(defn f [])

(println (testf f))