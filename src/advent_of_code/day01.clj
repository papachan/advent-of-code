(ns advent-of-code.day01
  (:require
   [clojure.test :refer [is]]
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defonce dir "day01/")

(defn str-to-long
  [i]
  (mapv parse-long i))

(defn read-chunks
  [input]
  (->> (-> (io/resource input)
           slurp
           (str/split #"\n\n"))
       (map #(str/split-lines %))
       (map str-to-long)))

(defn solution-1
  [records]
  (->> records
       (map #(apply + %))
       (apply max)))

(comment
  (is (= (solution-1 (read-chunks (str dir "part-1.txt"))) 24000))
  (let [res (read-chunks (str dir "part-1.txt"))]
    (solution-1 res)))

(defn solution-2
  [records]
  (->> records
       (map #(apply + %))
       (sort >)
       (take 3)
       (reduce +)))

(comment
  (let [res (read-chunks (str dir "part-2.txt"))]
    (solution-2 res)))

(defn run
  [& _]
  (let [res (->> (read-chunks (str dir "part-1.txt"))
                 solution-1)]
    (println (str "Solution 1 - " res))
    (is (= res 24000)))
  (let [res (->> (read-chunks (str dir "part-2.txt"))
                 solution-2)]
    (println (str "Solution 2 - " res))
    (is (= res 206104))))
