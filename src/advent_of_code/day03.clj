(ns advent-of-code.day03
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.set :as set]
            [clojure.test :refer [is]]))

(defonce dir "day03")

(defn read-input
  []
  (->> (io/resource (str dir "/" "input.txt"))
       (slurp)
       (str/split-lines)))

(defn divide-ruckstack
  [v]
  (let [[a b] (partition (/ (count v) 2) v)]
    (set/intersection (into #{} a)
                      (into #{} b))))

(defn alphabet-chars
  [characters]
  (->> characters
       (mapv #(->> % char str))
       (str/join)
       (map-indexed vector)))

(defonce alphabet-index
  (->> (for [[idx v] (alphabet-chars (concat
                                      (range (int \a) (inc (int \z)))
                                      (range (int \A) (inc (int \Z)))))]
         [v (inc idx)])
       (into {})))

(defn solve-1
  []
  (->> (read-input)
       (map divide-ruckstack)
       (mapv first)
       (mapv #(get alphabet-index %))
       (reduce +)))

(defn solve-2
  []
  (->> (read-input)
       (partition 3)
       (map (fn [m]
              (->> m
                   (map set)
                   (apply set/intersection))))
       (map first)
       (mapv #(get alphabet-index %))
       (reduce +)))

(defn run
  [& _]
  (let [res (solve-1)]
    (println (str "Solution 1 - " res))
    (is (= res 7821)))
  (let [res (solve-2)]
    (println (str "Solution 2 - " res))
    (is (= res 2752))))

(comment
  (run))
