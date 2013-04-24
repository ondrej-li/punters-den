(ns punters-den.user-test
    (:require [punters-den.core :as pc]
              [clojure.pprint :as pp])
    (:use clojure.test)
    (:use [ring.mock.request :only [request header]])
    (:use test.checkers)
    (:use midje.sweet))

(deftest status (fact "login-test"
           (let [response (pc/app (request :get "/user/login/" {:username "smith" :password "agent"}))]
           (pp/pprint response)
           response => OK
           (response :body) => "{\"status\":\"OK\",\"key\":\"123456789\"}")))
