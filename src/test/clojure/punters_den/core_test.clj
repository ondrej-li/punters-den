(ns punters-den.core-test
  (:require [punters-den.core :as sc])
  (:use clojure.test
        [ring.mock.request :only [request header]]
        [midje.sweet :only [facts contains defchecker]]))

(defchecker is-status [code]
  (contains {:status code}))

(deftest status (facts "Simple test"
           (let [response (sc/app (request :post "/punters-den/user" :body {:username "smith" :password "agent"}))]
             response => (is-status 200))))
