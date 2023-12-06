;; Write a function which returns a personalized greeting.

(defn testf [__]
  (and
   (= (__ "Dave") "Hello, Dave!")
   (= (__ "Jenn") "Hello, Jenn!")
   (= (__ "Rhea") "Hello, Rhea!")))

(def f)

(println (testf f))