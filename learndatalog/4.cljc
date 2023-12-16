;; https://www.learndatalogtoday.org/chapter/4

;; What attributes are associated with a given movie.
'[:find ?attr
  :in $ ?title
  :where
  [?m :movie/title ?title]
  [?m ?a]
  [?a :db/ident ?attr]]

;; Find the names of all people associated with a particular
;; movie (i.e. both the actors and the directors)
'[:find ?name
  :in $ ?title [?attr ...]
  :where
  [?m :movie/title ?title]
  [?a :db/ident ?attr]
  [?m ?a ?p]
  [?p :person/name ?name]]

;; Find all available attributes, their type and their
;; cardinality. This is essentially a query to find the schema of the
;; database. To find all installed attributes you must use
;; the :db.install/attribute attribute. You will also need to use
;; the :db/valueType and :db/cardinality attributes as well
;; as :db/ident.
'[:find ?attr ?type ?cardinality
  :where
  [_ :db.install/attribute ?a]
  [?a :db/valueType ?t]
  [?a :db/cardinality ?c]
  [?a :db/ident ?attr]
  [?t :db/ident ?type]
  [?c :db/ident ?cardinality]]

;; When was the seed data imported into the database? Grab the
;; transaction of any datom in the database, e.g., [_ :movie/title _
;; ?tx] and work from there.
'[:find ?inst
  :where
  [_ :movie/title _ ?tx]
  [?tx :db/txInstant ?inst]]
