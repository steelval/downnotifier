(ns downnotifier.routes.home
  (:require [compojure.core :refer :all]
            [downnotifier.layout :as layout]
            [downnotifier.util :as util]
            [downnotifier.db.core :as db]
            [org.httpkit.client :as http]
            [digest :refer :all]
            [clj-time.coerce :as crc]
            [noir.response :as resp]))

(defn home-page [& [url status error]]
  (layout/render "home.html"
                 {:error error
                  :url url
                  :status status}))

(defn check-url [url]
  (let [{:keys [status error]} @(http/get url)] (home-page url status error)))

(defn about-page []
  (layout/render "about.html"))

(defn get-prooflink [hashcode]
  (layout/render "prooflink.html"
                 {:prooflink (db/get-prooflink hashcode)}))

(defn create-prooflink [url]
  (let [hashcode (subs (digest/md5 (str (str (crc/to-long (new java.util.Date))) url)) 0 8)]
  (db/create-prooflink url hashcode (= 200 (@(http/get url) :status)))
  (resp/redirect (str "/prooflink?hashcode=" hashcode))))

(defroutes home-routes
  (GET "/" [] (home-page))
  (POST "/" [url] (check-url url))
  (GET "/create-prooflink" [url] (create-prooflink url))
  (GET "/prooflink" [hashcode] (get-prooflink hashcode))
  (GET "/about" [] (about-page)))
