(ns punters-den.user
    (:use compojure.core
        [clojure.tools.logging :only [info warn error]]
        ring.util.response)
    (:require [compojure.route :as route]
        [compojure.handler :as handler]))

(defn authenticate-user [username password]
    (info "authenticate-user called")
    (response {:status "OK" :key "123456789"}))

(defroutes login-routes
    (GET    "/" [username password] (authenticate-user username password)))

