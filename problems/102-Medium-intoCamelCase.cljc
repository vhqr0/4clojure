;; When working with java, you often need to create an object with <code>fieldsLikeThis</code>, but you'd rather work with
;; a hashmap that has <code>:keys-like-this</code> until it's time to convert. Write a function which takes lower-case
;; hyphen-separated strings and converts them to camel-case strings.

(defn testf [__]
  (and
   (= (__ "something") "something")
   (= (__ "multi-word-key") "multiWordKey")
   (= (__ "leaveMeAlone") "leaveMeAlone")))

(def f)

(println (testf f))