(ns punters-den.core-test
    (:require [punters-den.core :as pc])
    (:use clojure.test)
    (:use [ring.mock.request :only [request header]])
    (:use test.checkers)
    (:use midje.sweet))

(deftest status (facts "login-test"
           (let [response (pc/app (request :get "/user/login/" {:username "smith" :password "agent"}))]
             response => (is-status 200))))
