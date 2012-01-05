;;; ch1_17.clj --- design a multiplication function analogous to fast-expt that
;;; runs in logarithmic time. This also solves 1_18
;;; Author: Vedang Manerikar
;;; Created on: 05 Jan 2012
;;; Time-stamp: "2012-01-05 22:50:43 vedang"


(ns projclojure.sicp.ch1.ch1_17)


(defn- double
  [n]
  (* n 2))


(defn- halve
  [n]
  (/ n 2))


(defn mult
  [a b]
  (if (= b 0)
    0
    (+ a (mult a (- b 1)))))


(defn mult-iter
  ([a b]
     (mult-iter a b 0))
  ([a b sum]
     (if (= b 0)
       sum
       (recur a (- b 1) (+ sum a)))))


(defn fast-mult
  "Recursive fast multiplication
a * b = 2a * b/2 ; b is even
      = a + (a * (b - 1)) ; b is odd"
  [a b]
  (cond
    (= b 0) 0
    (even? b) (fast-mult (double a) (halve b))
    :else (+ a (fast-mult a (- b 1)))))


(defn fast-mult-iter
  "iterative fast multiplication"
  ([a b]
     (fast-mult-iter a b 0))
  ([a b acc]
     (cond
       (= b 0) acc
       (even? b) (recur (double a) (halve b) acc)
       :else (recur a (- b 1) (+ acc a)))))
