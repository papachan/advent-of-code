(ns advent-of-code.core
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]
   [advent-of-code.day01 :as day01]
   [advent-of-code.day02 :as day02])
  (:gen-class))

(defn run-problem
  [data]
  (when-let [fun (ns-resolve *ns* (symbol (str "advent-of-code." (:day data) "/run")))]
    (apply fun nil)))

(defn -main
  [& args]
  (when (empty? args)
    (throw (ex-info "Need an argument to define the day to process" {}))
    (run-problem {:day (first args)})))
