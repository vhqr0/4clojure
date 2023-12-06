(require
  hyrule :readers * *)

(import
  operator
  itertools
  io
  textwrap
  json)

(defn sluggify [#* args]
  (.join ""
         (ap-filter (or (= it "-") (.isalnum it))
                    (.join "-" (itertools.chain #* (map (operator.methodcaller "split") args))))))

(defn problem-id [problem]
  (let [id (get problem "_id")]
    (ebranch (isinstance id it)
             int id
             dict (int (get id "$numberLong")))))

(defn problem-path [base problem]
  (.format "{}/{:03}-{}.cljc" base (problem-id problem) (sluggify (get problem "difficulty") (get problem "title"))))

(defn problem-content [problem]
  (let [sio (io.StringIO)]
    (for [line (textwrap.wrap (.join " " (.split (get problem "description"))) :width 120)]
      (print (.format ";; {}" line) :file sio))
    (.write sio "\n")
    (ap-if (.get problem "restricted")
           (print ";; restricted:" #* it "\n" :file sio))
    (.write sio "(defn test [__]\n  (and")
    (for [test (get problem "tests")]
      (.write sio "\n   ")
      (.write sio (.join " " (.split test))))
    (.write sio "))\n\n(defn f [])\n\n(println (testf f))")
    (.getvalue sio)))

(defn extract-problem [base problem]
  (with [f (open (problem-path base problem) "w")]
    (.write f (problem-content problem))))

;; (defmain [_ [input-path "problems.json"] [output-path "problems"]]
;;   (ap-each (with [f (open input-path)] (json.load f))
;;            (extract-problem output-path it)))
