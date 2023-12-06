;; Write a function which returns a personalized greeting.

(defn test [__]
  (and
   (= (__ "Dave") "Hello, Dave!")
   (= (__ "Jenn") "Hello, Jenn!")
   (= (__ "Rhea") "Hello, Rhea!")))

(defn f [])

(println (testf f))