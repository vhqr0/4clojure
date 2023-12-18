;; https://www.learndatalogtoday.org/chapter/8

;; Write a rule [movie-year ?title ?year] where ?title is the title of
;; some movie and ?year is that movies release year.
'[:find ?title
  :in $ %
  :where
  [movie-year ?title 1991]]
'[[(movie-year ?title ?year)
   [?m :movie/title ?title]
   [?m :movie/year ?year]]]

;; Two people are friends if they have worked together in a
;; movie. Write a rule [friends ?p1 ?p2] where p1 and p2 are person
;; entities. Try with a few different ?name inputs to make sure you
;; got it right. There might be some edge cases here.
'[:find ?friend
  :in $ % ?name
  :where
  [?p1 :person/name ?name]
  (friends ?p1 ?p2)
  [?p2 :person/name ?friend]]
'[[(friends ?p1 ?p2)
   [?m :movie/cast ?p1]
   [?m :movie/cast ?p2]
   [(not= ?p1 ?p2)]]
  [(friends ?p1 ?p2)
   [?m :movie/director ?p1]
   [?m :movie/director ?p2]]
  [(friends ?p1 ?p2)
   [?m :movie/cast ?p1]
   [?m :movie/director ?p2]]
  [(friends ?p1 ?p2)
   [?m :movie/director ?p1]
   [?m :movie/cast ?p2]]]

;; Write a rule [sequels ?m1 ?m2] where ?m1 and ?m2 are movie
;; entities. You'll need to use the attribute :movie/sequel. To
;; implement this rule correctly you can think of the problem like
;; this: A movie ?m2 is a sequel of ?m1 if either

;; ?m2 is the "direct" sequel of m1 or

;; ?m2 is the sequel of some movie ?m and that movie ?m is the sequel to ?m1.

;; There are (at least) three different ways to write the above
;; query. Try to find all three solutions.
'[:find ?sequel
  :in $ % ?title
  :where
  [?m :movie/title ?title]
  (sequels ?m ?s)
  [?s :movie/title ?sequel]]
'[[(sequels ?m1 ?m2)
   [?m1 :movie/sequel ?m2]]
  [(sequels ?m1 ?m2)
   (sequels ?m1 ?m)
   [?m :movie/sequel ?m2]]]
