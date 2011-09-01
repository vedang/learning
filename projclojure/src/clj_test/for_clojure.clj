;;; for_clojure.clj --- solutions to 4clojure.com problems
;;; Author: Vedang Manerikar
;;; Created on: 30 Aug 2011
;;; Time-stamp: "2011-08-31 16:40:00 vedang"


(ns clj-test.for-clojure)


(defn prob22
  "Write a function which returns the total number of elements in a sequence."
  [xs]
  (reduce (fn [a b]
            (inc a)) 0 xs))


(defn prob23
  "Write a function which reverses a sequence."
  [xs]
  (reduce (fn [acc x]
            (conj acc x)) () xs))


(defn prob24
  "Write a function which returns the sum of a sequence of numbers."
  [xs]
  (reduce + xs))


(defn prob25
  "Write a function which returns only the odd numbers from a sequence."
  [xs]
  (filter odd? xs))


(defn prob26
  "Write a function which returns the first X fibonacci numbers.
X must be at least 1"
  [n]
  (concat [1] ))
