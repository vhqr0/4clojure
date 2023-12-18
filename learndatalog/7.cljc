;; https://www.learndatalogtoday.org/chapter/7

;; count the number of movies in the database
'[:find (count ?m)
  :where
  [?m :movie/title]]

;; Find the birth date of the oldest person in the database.
'[:find (min ?born)
  :where
  [_ :person/born ?born]]

;; Given a collection of actors and (the now familiar) ratings
;; data. Find the average rating for each actor. The query should
;; return the actor name and the avg rating.
'[:find ?name (avg ?rating)
  :in $ [?name ...] [[?title ?rating] ...]
  :where
  [?p :person/name ?name]
  [?m :movie/title ?title]
  [?m :movie/cast ?p]]
