(ns punters-den.util
  (:use ring.util.response)
  (:use [clojure.tools.logging :only [info error]])
  (:require [clojure.java.jdbc :as sql])
  (:require [clojure.pprint :as pp])
  (:import punters_den.util.Factory)
  (:import java.text.SimpleDateFormat))

(defn now []
  (java.util.Date.))

(defn formatted-now []
  (.format (SimpleDateFormat. "yyyy-MM-dd HH:mm:ss") (now)))

(defn wrap-status
  [handler]
  (fn [request]
    (-> (handler request))))

(def error-codes
  {:invalid 400 :not-found 404})

(defn wrap-error-handling
  "handles errors if any found"
  [handler]
  (fn [request]
    (try
      (or (handler request)
        (status (response {"error" "resource not found"}) 404))
      (catch Exception e
        (do
          (pp/pprint e)
          (status (response {"error" "malformed json"}) 500))))))

(defn insert-table
  "insert values into table"
  [table values]
  (assoc values :id (->>
                      {:created_date (now)}
                      (merge values)
                      (sql/insert-records table)
                      (sql/with-connection {:datasource (Factory/getDataSource)})
                      (first)
                      (:generated_key ))))

(defn update-table
  "updates table with values"
  [table condition values]
  (sql/with-connection {:datasource (Factory/getDataSource)}
    (sql/update-values table condition (merge values {:updated_date (formatted-now)}))))
