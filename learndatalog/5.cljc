;; https://www.learndatalogtoday.org/chapter/5

;; Find movies older than a certain year (inclusive)
'[:find ?title
  :in $ ?year
  :where
  [?m :movie/title ?title]
  [?m :movie/year ?y]
  [(<= ?y ?year)]]

;; Find actors older than Danny Glover
'[:find ?name
  :where
  [?dg :person/name "Danny Glover"]
  [?dg :person/born ?dg-born]
  [_ :movie/cast ?p]
  [?p :person/born ?born]
  [?p :person/name ?name]
  [(< ?born ?dg-born)]]

;; Find movies newer than ?year (inclusive) and has a ?rating higher
;; than the one supplied
'[:find ?title
  :in $ ?year ?rating [[?title ?r] ...]
  :where
  [?m :movie/title ?title]
  [?m :movie/year ?y]
  [(> ?y ?year)]
  [(> ?r ?rating)]]
