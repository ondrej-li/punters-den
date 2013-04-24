(ns punters-den.core
    (:use compojure.core)
    (:use [clojure.tools.logging :only [info]])
    (:use punters-den.user)
    (:use ring.util.response)
    (:require   [compojure.handler :as handler]
                [ring.middleware.json :as middleware]
                [clojure.java.jdbc :as sql]
                [compojure.route :as route]
                [org.lpetit.ring.servlet.util :as sc-util]))

(defn report-status []
    (response {:status "OK"}))

(defroutes app-routes
    (context "/api/user/login" [] login-routes)
    (context "/" [] (defroutes status-routes (
        OPTIONS "/" [] (report-status))))
    (route/files "/public")
    (route/not-found "Not Found"))

(def app
    (-> (handler/api app-routes)
        (middleware/wrap-json-body)
        (middleware/wrap-json-response)))

(defn start [ctx]
    (info "Starting webapp with" (sc-util/context-params ctx)))

(defn stop [ctx]
    (info "Stopping webapp with" (sc-util/context-params ctx)))

