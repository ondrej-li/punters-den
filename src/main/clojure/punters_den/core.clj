(ns punters-den.core
    (:use compojure.core)
    (:use [clojure.tools.logging :only [info]])
    (:use punters-den.user)
    (:use ring.util.response)
    (:require   [compojure.handler :as handler]
                [ring.middleware.json :as middleware]
                [clojure.java.jdbc :as sql]
                [compojure.route :as route]))

(defn report-status []
    (response {:status "OK"}))

(defroutes app-routes
    (context "/user/login" [] login-routes)
    (context "/" [] (defroutes status-routes (
        OPTIONS "/" [] (report-status))))
    (route/files "/public")
    (route/not-found "Not Found"))

(def app
    (-> (handler/api app-routes)
        (middleware/wrap-json-body)
        (middleware/wrap-json-response)))

