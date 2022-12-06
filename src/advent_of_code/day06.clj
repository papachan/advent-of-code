(ns advent-of-code.day06
  (:require
   [clojure.java.io :as io]
   [clojure.test :as test :refer [is]]))

(defn read-input
  [dir]
  (->> (io/resource (str dir "/" "input.txt"))
       slurp))

(defn different? [xs]
  (= (count xs)
     (count (set xs))))

(defn solve
  [data n]
  (->> data
       (partition n 1)
       (take-while (complement different?))
       count
       (+ n)))

(defn run
  [& args]
  (let [res (solve (read-input (first args)) 4)]
    (println "Solution 1:" res)
    (is (= 1779 res)))
  (let [res (solve (read-input (first args)) 14)]
    (println "Solution 2:" res)
    (is (= 2635 res))))
