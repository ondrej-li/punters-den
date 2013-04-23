(ns punters-den.user
    (:use compojure.core)
    (:use [clojure.tools.logging :only [info error]])
    (:use ring.util.response)
    (:require   [compojure.route :as route]
                [clojure.java.jdbc :as sql]
                [compojure.handler :as handler]))

(defn authenticate-user [username password]
    (info "authenticate-user called")
    (response {:status "OK" :key "123456789"}))

(defroutes login-routes
    (GET    "/" [username password] (authenticate-user username password)))

