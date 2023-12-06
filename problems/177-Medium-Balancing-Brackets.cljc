;; When parsing a snippet of code it's often a good idea to do a sanity check to see if all the brackets match up. Write a
;; function that takes in a string and returns truthy if all square [ ] round ( ) and curly { } brackets are properly
;; paired and legally nested, or returns falsey otherwise.

(defn test [__]
  (and
   (__ "This string has no brackets.")
   (__ "class Test { public static void main(String[] args) { System.out.println(\"Hello world.\"); } }")
   (not (__ "(start, end]"))
   (not (__ "())"))
   (not (__ "[ { ] } "))
   (__ "([]([(()){()}(()(()))(([[]]({}()))())]((((()()))))))")
   (not (__ "([]([(()){()}(()(()))(([[]]({}([)))())]((((()()))))))"))
   (not (__ "["))))

(defn f [])

(println (testf f))