(ns punters-den.user-test
  (:require [punters-den.core :as pc]
            [clojure.pprint :as pp]
            [clojure.java.jdbc :as sql])
  (:use clojure.test)
  (:use [ring.mock.request :only [request header]])
  (:use test.checkers)
  (:use midje.sweet)
  (:import punters_den.util.Configuration)
  (:import punters_den.util.Factory)
  (:import punters_den.util.FlywayService))

(defn create-dummy-user []
  (sql/with-connection {:datasource (Factory/getDataSource)}
    (sql/insert-record :user {:username "smith" :password "agent" :email "agent.smith@gmail.com" :status "open"})))

(defn delete-dummy-user []
  (sql/with-connection {:datasource (Factory/getDataSource)}
    (sql/delete-rows :user ["username = ?" "smith"])))

(deftest status
  (fact "login-test"
    (let [_ (delete-dummy-user)
          _ (create-dummy-user)
          response (pc/app (request :get "/api/user/smith/login" {:password "agent"}))
          _ (delete-dummy-user)]
      (pp/pprint response)
      response => OK)))

(defn one-time-setup
  "setup database (once a test-suite)"
  []
  (FlywayService/initializeDatabase (Factory/getDataSource) nil))

(defn once-fixture
  [f]
  (one-time-setup)
  (f))

;; register as a one-time callback
(use-fixtures :once once-fixture)
