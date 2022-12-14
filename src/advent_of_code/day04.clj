(ns advent-of-code.day04
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn data
  [dir]
  (->> (io/resource (str dir "/" "input.txt"))
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
  [& args]
  (let [res (count (filter fully-contains? (data (first args))))]
    (println (str "Solution 1 - " res))
    (is (= 518 res)))
  (let [res (count (filter overlapped? (data (first args))))]
    (println (str "Solution 2 - " res))
    (is (= 909 res))))

(comment
  (run '("day04")))
