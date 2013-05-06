(ns punters-den.util-test
  (:require [punters-den.core :as pc]
            [clojure.pprint :as pp]
            [clojure.java.jdbc :as sql])
  (:use clojure.test)
  (:use [ring.mock.request :only [request header]])
  (:use test.checkers)
  (:use midje.sweet)
  (:use punters-den.util)
  (:import punters_den.util.Configuration)
  (:import punters_den.util.Factory)
  (:import punters_den.util.FlywayService))

(deftest formatted-now-ok
  (fact "now-test"
    (let [now (formatted-now)]
      (pp/pprint now)
      (clojure.string/blank? now) => false)))

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
