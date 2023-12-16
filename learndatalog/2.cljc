;; https://www.learndatalogtoday.org/chapter/2

;; Find movie titles made in 1985
'[:find ?title
  :where
  [?m :movie/year 1985]
  [?m :movie/title ?title]]

;; What year was "Alien" released?
'[:find ?year
  :where
  [?m :movie/title "Alien"]
  [?m :movie/year ?year]]

;; Who directed RoboCop? You will need to
;; use [<movie-eid> :movie/director <person-eid>] to find the director
;; for a movie.
'[:find ?name
  :where
  [?m :movie/title "RoboCop"]
  [?m :movie/director ?p]
  [?p :person/name ?name]]

;; Find directors who have directed Arnold Schwarzenegger in a movie.
'[:find ?name
  :where
  [?p :person/name "Arnold Schwarzenegger"]
  [?m :movie/cast ?p]
  [?m :movie/director ?d]
  [?d :person/name ?name]]
