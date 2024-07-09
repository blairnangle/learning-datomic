(require '[datomic.client.api :as d])

(def client (d/client {:server-type :datomic-local
                       :system      "dev"}))


;; ephemeral storage in CI/CD
;(def client (d/client {:server-type :datomic-local
;                       :storage-dir :mem
;                       :system "ci"}))

(comment
  (d/create-database client {:db-name "movies"})

  (def conn (d/connect client {:db-name "movies"}))

  (def movie-schema [{:db/ident       :movie/title
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "The title of the movie"}

                     {:db/ident       :movie/genre
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "The genre of the movie"}

                     {:db/ident       :movie/release-year
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "The year the movie was released in theaters"}])

  (d/transact conn {:tx-data movie-schema})

  (def first-movies [{:movie/title        "The Goonies"
                      :movie/genre        "action/adventure"
                      :movie/release-year 1985}
                     {:movie/title        "Commando"
                      :movie/genre        "thriller/action"
                      :movie/release-year 1985}
                     {:movie/title        "Repo Man"
                      :movie/genre        "punk dystopia"
                      :movie/release-year 1984}])

  (d/transact conn {:tx-data first-movies})

  (def db (d/db conn))

  (def all-titles-q '[:find ?movie-whatever
                      :where [_ :movie/title ?movie-whatever]])

  (d/q all-titles-q db)

  (def second-movies [{:movie/title        "Sicario"
                       :movie/genre        "action/adventure"
                       :movie/release-year 2015}
                      {:movie/title        "Sicario 2"
                       :movie/genre        "action/adventure"
                       :movie/release-year 2018}])

  (d/transact conn {:tx-data second-movies})

  (d/pull db '[*] 74766790688849)

  (d/datoms db {:index      :eavt
                :components []})

  (d/list-databases db {})
  )
