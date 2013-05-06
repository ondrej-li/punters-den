(ns punters-den.core
  (:use compojure.core)
  (:use [clojure.tools.logging :only [info]])
  (:use punters-den.user)
  (:use punters-den.util)
  (:use ring.util.response)
  (:require [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [ring.middleware.cookies :as cookies]
            [clojure.java.jdbc :as sql]
            [compojure.route :as route]
            [org.lpetit.ring.servlet.util :as sc-util]))

(defn report-status []
  (response {:status "OK"}))

(defroutes app-routes
  (context "/api/user" [] user-routes)
  (context "/" [] (defroutes status-routes (
                                             OPTIONS "/" [] (report-status))))
  (route/files "/public")
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
    ;(wrap-error-handling)
    (wrap-status)
    (middleware/wrap-json-body)
    (cookies/wrap-cookies)
    (middleware/wrap-json-response)))

(defn start [ctx]
  (info "Starting webapp with" (sc-util/context-params ctx)))

(defn stop [ctx]
  (info "Stopping webapp with" (sc-util/context-params ctx)))

