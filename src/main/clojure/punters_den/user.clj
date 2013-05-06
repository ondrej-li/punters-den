(ns punters-den.user
  (:use compojure.core)
  (:use [clojure.tools.logging :only [info error]])
  (:use [clojure.contrib.core :only [dissoc-in]])
  (:use ring.util.response)
  (:use punters-den.util)
  (:require [compojure.route :as route]
            [clojure.java.jdbc :as sql]
            [compojure.handler :as handler])
  (:import punters_den.util.Factory))

(def ^{:dynamic true :doc "stores user details per request"} *user*)

(defn user-by-key
  "returns first user row matching the key"
  [auth-key]
  (sql/with-connection {:datasource (Factory/getDataSource)}
    (sql/with-query-results rs ["select * from user auth_key = ?" auth-key]
      (first (doall rs)))))

(defn wrap-user
  "associates user with the current request"
  [handler]
  (fn [request]
    (let [auth-key (user-by-key (:auth-key (:cookies request)))]
      (cond
        (= auth-key nil) (do (info "unauthenticated user") (binding [*user* ""]))
        :else (binding [*user* (user-by-key auth-key)])))))

(defn validate-username-password
  ""
  [username password]
  (sql/with-connection {:datasource (Factory/getDataSource)}
    (sql/with-query-results users ["select * from user where username = ? and password = ?" username password]
      (first users))))

(defn update-auth-key
  "updates user record with "
  [username auth-key]
  (update-table :user ["username=?" username]
    {:auth_key auth-key :last_login (formatted-now)}))

(defn generate-user-key
  "Generate unique cookie"
  [username]
  (str username (java.util.UUID/randomUUID)))

(defn login-user
  [username password]
  (info "authenticate-user called")
  (print "login")
  (let [auth-key (generate-user-key username)
        user (validate-username-password username password)]
    (cond
      (not= user nil) (update-auth-key username auth-key)
      :else (throw (Exception. "unknown username/password")))
    (assoc-in (response {:outcome "OK"}) [:header :cookies :auth-key ] auth-key)))

(defn logout-user []
  (info "logout-user called")
  (assoc-in (response {:outcome "OK"}) [:header :cookies :auth-key ] nil))

(defroutes user-routes
  (GET "/login" [username password] (login-user username password))
  (GET "/logout" [] (logout-user))
  ;(POST "/" [username password] (create-user username password))
  ;(GET "/[:id]" [id] (user-by-id id))
  ;(PUT "/[:id]" [id] (update-user-by-id id password))
  ;(DELETE "/[:id]" [id] (delete-user-by-id id))
  )

