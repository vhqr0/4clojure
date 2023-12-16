;; https://www.learndatalogtoday.org/chapter/1

;; Find the entity ids of movies made in 1987
'[:find ?m
  :where
  [?m :movie/year 1987]]

;; Find the entity-id and titles of movies in the database
'[:find ?m ?title
  :where
  [?m :movie/title ?title]]

;; Find the name of all people in the database
'[:find ?name
  :where
  [_ :person/name ?name]]
