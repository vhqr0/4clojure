;; https://www.learndatalogtoday.org/chapter/3

;; Find movie title by year
'[:find ?title
  :in $ ?year
  :where
  [?m :movie/year ?year]
  [?m :movie/title ?title]]

;; Given a list of movie titles, find the title and the year that
;; movie was released.
'[:find ?title ?year
  :in $ [?title ...]
  :where
  [?m :movie/title ?title]
  [?m :movie/year ?year]]

;; Find all movie ?titles where the ?actor and the ?director has
;; worked together
'[:find ?title
  :in $ ?actor ?director
  :where
  [?a :person/name ?actor]
  [?d :person/name ?director]
  [?m :movie/cast ?a]
  [?m :movie/director ?d]
  [?m :movie/title ?title]]

;; Write a query that, given an actor name and a relation with
;; movie-title/rating, finds the movie titles and corresponding rating
;; for which that actor was a cast member.
'[:find ?title ?rating
  :in $ ?actor [[?title ?rating] ...]
  :where
  [?p :person/name ?actor]
  [?m :movie/title ?title]
  [?m :movie/cast ?p]]
