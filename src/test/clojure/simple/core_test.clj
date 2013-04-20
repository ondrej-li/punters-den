(ns simple.core-test
  (:require [simple.core :as sc])
  (:use clojure.test
        [ring.mock.request :only [request header]]
        [midje.sweet :only [facts contains defchecker]]))

(defchecker is-status [code]
  (contains {:status code}))

(deftest status (facts "Simple test"
           (let [response (sc/app (request :get "/punters-den/"))]
             response => (is-status 200))))
