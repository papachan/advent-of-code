(ns advent-of-code.core
  (:require
   [clojure.test :refer [is]]
   [clojure.java.io :as io]
   [clojure.string :as str])
  (:gen-class))

(defn read-file-by-integers
  [input]
  (->> (io/resource input)
       slurp
       (re-seq #"\d+")
       (mapv #(Integer/parseInt %))))

(defn -main
  [& args]
  (println "hola"))
