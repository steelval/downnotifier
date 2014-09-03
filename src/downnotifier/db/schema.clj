(ns downnotifier.db.schema
  (:require [clojure.java.jdbc :as sql]
            [noir.io :as io]))

(def db-store "site.db")

(def db-spec {:classname "org.h2.Driver"
              :subprotocol "h2"
              :subname (str (io/resource-path) db-store)
              :user "sa"
              :password ""
              :make-pool? true
              :naming {:keys clojure.string/lower-case
                       :fields clojure.string/upper-case}})
(defn initialized?
  "checks to see if the database schema is present"
  []
  (.exists (new java.io.File (str (io/resource-path) db-store ".mv.db"))))


(defn create-users-table []
  (sql/db-do-commands
   db-spec
   (sql/create-table-ddl
    :users
    [:id "INTEGER PRIMARY KEY AUTO_INCREMENT"]
    [:email "varchar(30)"])))


(defn create-prooflinks-table []
  (sql/db-do-commands
   db-spec
   (sql/create-table-ddl
    :prooflinks
    [:id "INTEGER PRIMARY KEY AUTO_INCREMENT"]
    [:url "varchar(200)"]
    [:hashcode "varchar(8)"]
    [:timestamp :timestamp]
    [:status :boolean])))

(defn create-tables
  "creates the database tables used by the application"
  []
  (create-users-table)
  (create-prooflinks-table))

