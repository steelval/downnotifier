(ns downnotifier.db.core
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [downnotifier.db.schema :as schema]))

(defdb db schema/db-spec)

(defentity prooflinks)

(defn create-prooflink
  [url hashcode status]
  (insert prooflinks
          (values {:url url
                   :hashcode hashcode
                   :timestamp (new java.util.Date)
                   :status status})))

(defn get-prooflink [hashcode]
  (first (select prooflinks
    (fields :hashcode :status :url :timestamp)
    (where {:hashcode hashcode}))))

