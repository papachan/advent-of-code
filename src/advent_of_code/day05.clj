(ns advent-of-code.day05
  (:require
   [clojure.test :as test :refer [is]]
   [clojure.java.io :as io]
   [clojure.string :as str]
   [clojure.walk :as walk]))

(defn read-input
  [dir]
  (->> (io/resource (str dir "/" "input.txt"))
       slurp))

(defn transpose
  [pad coll]
  (->> coll
       (map #(concat % (repeat ::end)))
       (apply map vector)
       (take-while #(not-every? #{::end} %))
       (walk/postwalk-replace {::end pad})))

(defn select-top-crates
  [data]
  (transduce (comp (map second) (map last)) str (sort data)))

(defn solve
  [op instructions crates]
  (->> instructions
       (reduce (fn [stacks [n from to]]
                 (-> stacks
                     (update to concat (op (take-last n (get stacks from))))
                     (update from (partial drop-last n))))
               crates)
       (select-top-crates)))

(defn parse-input
  [data]
  (let [[stacks moves] (-> data
                           (str/split #"\n\n"))
        instructions   (->> moves
                            str/split-lines
                            (map #(let [[[_ qty from to]]
                                        (re-seq #"^move (\d+) from (\d+) to (\d+)" %)]
                                    (->> [qty from to]
                                         (mapv parse-long)))))
        crates         (->> stacks
                            (str/split-lines)
                            (map #(transduce (comp (drop 1) (take-nth 4)) conj %))
                            drop-last
                            (transpose \space)
                            (map reverse)
                            (map #(remove #{\space} %))
                            (map vector (drop 1 (range)))
                            (into {}))]
    [instructions crates]))

(defn solve-1
  [day]
  (let [[instructions crates] (-> day
                                  read-input
                                  parse-input)]
  (solve #'reverse instructions crates)))

(defn solve-2
  [day]
  (let [[instructions crates] (-> day
                                  read-input
                                  parse-input)]
  (solve #'identity instructions crates)))

(defn run
  [& args]
  (let [res (solve-1 (first args))]
    (println "Solution 1:" res)
    (is (= res "JDTMRWCQJ")))
  (let [res (solve-2 (first args))]
    (println "Solution 2:" res)
    (is (= res "VHJDDCWRD"))))

(comment
  (run ["day05"])"")
