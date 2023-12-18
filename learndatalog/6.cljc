;; https://www.learndatalogtoday.org/chapter/6

;; Find people by age. Use the function tutorial.fns/age to find the
;; age given a birthday and a date representing "today".
'[:find ?name
  :in $ ?age ?today
  :where
  [?p :person/name ?name]
  [?p :person/born ?born]
  [(tutorial.fns/age ?born ?today) ?age]]

;; Find people younger than Bruce Willis and their ages.
'[:find ?name ?age
  :in $ ?today
  :where
  [?bw :person/name "Bruce Willis"]
  [?bw :person/born ?b]
  [?p :person/name ?name]
  [?p :person/born ?born]
  [(> ?born ?b)]
  [(tutorial.fns/age ?born ?today) ?age]]

;; The birthday paradox states that in a room of 23 people there is a
;; 50% chance that someone has the same birthday. Write a query to
;; find who has the same birthday. Use the < predicate on the names to
;; avoid duplicate answers. You can use (the deprecated) .getDate and
;; .getMonth java Date methods.
'[:find ?name-1 ?name-2
  :where
  [?p1 :person/name ?name-1]
  [?p2 :person/name ?name-2]
  [?p1 :person/born ?born-1]
  [?p2 :person/born ?born-2]
  [(.getDate ?born-1) ?date-1]
  [(.getDate ?born-2) ?date-2]
  [(= ?date-1 ?date-2)]
  [(.getMonth ?born-1) ?month-1]
  [(.getMonth ?born-2) ?month-2]
  [(= ?month-1 ?month-2)]
  [(< ?name-1 ?name-2)]]
