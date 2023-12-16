;; https://www.learndatalogtoday.org/chapter/0

;; Find all movies titles in the database.
'[:find ?title
  :where
  [_ :movie/title ?title]]
