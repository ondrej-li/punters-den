(ns punters-den.user
    (:use compojure.core
        [clojure.tools.logging :only [info]])
    (:require [compojure.route :as route]
        [compojure.handler :as handler]))

(defn authenticate-user [username password]
    {:status "OK" :key "123456789"})

(defroutes login-routes
    (PUT    "/" [username password] (authenticate-user username password)))

