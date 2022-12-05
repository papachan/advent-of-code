(ns advent-of-code.day03
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.set :as set]
            [clojure.test :refer [is]]))

(defn read-input
  [dir]
  (->> (io/resource (str dir "/" "input.txt"))
       (slurp)
       (str/split-lines)))

(defn divide-ruckstack
  [v]
  (let [[a b] (split-at (/ (count v) 2) v)]
    (set/intersection (into #{} a)
                      (into #{} b))))

(defn solve-1
  [day]
  (->> (read-input day)
       (map divide-ruckstack)
       (mapv first)
       (map (fn [c]
              (-> (int c) (- 96) (mod 58))))
       (reduce +)))

(defn solve-2
  [day]
  (->> (read-input day)
       (partition 3)
       (map (fn [m]
              (->> m
                   (map set)
                   (apply set/intersection))))
       (map first)
       (map (fn [c]
              (-> (int c) (- 96) (mod 58))))
       (reduce +)))

(defn run
  [& args]
  (let [res (solve-1 (first args))]
    (println (str "Solution 1 - " res))
    (is (= res 7821)))
  (let [res (solve-2 (first args))]
    (println (str "Solution 2 - " res))
    (is (= res 2752))))

(comment
  (run '("day03")))
