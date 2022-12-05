(ns advent-of-code.day04
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defonce dir "day04")

(def data (->> (io/resource (str dir "/" "input.txt"))
               slurp
               (re-seq #"\d+")
               (map parse-long)
               (partition 4)))

(defn fully-contains? [[a b c d]]
  (or (<= a c d b)
      (<= c a b d)))

(defn overlapped? [[a b c d]]
  (and (<= a d) (<= c b)))

(defn run
  [& _]
  (let [res (count (filter fully-contains? data))]
    (println (str "Solution 1 - " res))
    (is (= 518 res)))
  (let [res (count (filter overlapped? data))]
    (println (str "Solution 2 - " res))
    (is (= 909 res))))

(comment
  (run))
