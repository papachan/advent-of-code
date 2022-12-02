(ns advent-of-code.day02
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defonce dir "day02")

(defn read-challenge [input]
  (-> (io/resource (str dir "/" input))
       slurp
       str/split-lines))

(def game01->scores
  #(case %
     "A X" (+ 1 3)
     "B X" (+ 1 0)
     "C X" (+ 1 6)
     "A Y" (+ 2 6)
     "B Y" (+ 2 3)
     "C Y" (+ 2 0)
     "A Z" (+ 3 0)
     "B Z" (+ 3 6)
     "C Z" (+ 3 3)
     nil))

(def game02->scores
  #(case %
     "A X" (+ 0 3)
     "B X" (+ 0 1)
     "C X" (+ 0 2)
     "A Y" (+ 3 1)
     "B Y" (+ 3 2)
     "C Y" (+ 3 3)
     "A Z" (+ 6 2)
     "B Z" (+ 6 3)
     "C Z" (+ 6 1)
     nil))

(defn run
  [& _]
  (let [res (->> (read-challenge "input.txt")
                 (map game01->scores)
                 (reduce +))]
    (println (str "Solution 1 - " res))
    (is (= res 15632)))
  (let [res (->> (read-challenge "input.txt")
                 (map game02->scores)
                 (reduce +))]
    (println (str "Solution 2 - " res))
    (is (= res 14416))))
