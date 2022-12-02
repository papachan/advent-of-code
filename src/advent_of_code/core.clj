(ns advent-of-code.core
  (:require
   [clojure.test :refer [is]]
   [clojure.java.io :as io]
   [clojure.string :as str]
   [advent-of-code.day01 :as day01]
   [advent-of-code.day02 :as day02])
  (:gen-class))

(defn run-problem
  [data]
  (when-let [fun (ns-resolve *ns* (symbol (str "advent-of-code." (:day data) "/run")))]
    (apply fun nil)))

(defn read-content->integers
  [input]
  (->> (io/resource input)
       slurp
       (re-seq #"\d+")
       (mapv parse-long)))

(defn -main
  [& args]
  (println "Hola" ))
