(ns punters-den.core
    (:use compojure.core
        [clojure.tools.logging :only [info]])
    (:use punters-den.user)
    (:use ring.util.response)
    (:require [compojure.handler :as handler]
        [ring.middleware.json :as middleware]
        [clojure.java.jdbc :as sql]
        [compojure.route :as route]))

(defroutes app-routes
    (context "/user/login" [] login-routes)
    (route/not-found "Not Found"))

(def app
    (-> (handler/api app-routes)
    (middleware/wrap-json-body)
    (middleware/wrap-json-response)))

