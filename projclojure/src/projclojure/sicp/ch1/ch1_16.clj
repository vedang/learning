;;; ch1_16.clj --- design an iterative exponentiation function that runs in
;;; O(log n) time
;;; Author: Vedang Manerikar
;;; Created on: 03 Jan 2012
;;; Time-stamp: "2012-01-03 23:56:45 vedang"


(ns projclojure.sicp.ch1.ch1_16)


(defn- square
  [n]
  (* n n))


(defn fast-expt
  "Iterative fast expt"
  ([b n]
     (fast-expt b n 1))
  ([b n acc]
     (cond
       (= n 0) acc
       (even? n) (recur (square b) (/ n 2) acc)
       :else (recur b (- n 1) (* acc b)))))
