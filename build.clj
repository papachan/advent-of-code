(ns build
  (:refer-clojure :exclude [test])
  (:require [org.corfield.build :as bb]))

(def lib 'net.clojars.core/advent-of-code-2022)
(def main 'core.advent-of-code-2022)

(defn test "Run the tests." [opts]
  (bb/run-tests opts))

(defn ci "Run the CI pipeline of tests (and build the uberjar)." [opts]
  (-> opts
      (assoc :lib lib :main main)
      (bb/run-tests)
      (bb/clean)
      (bb/uber)))
