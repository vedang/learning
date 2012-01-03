;;; notes.clj --- random notes and code from the chapter
;;; Author: Vedang Manerikar
;;; Created on: 03 Jan 2012
;;; Time-stamp: "2012-01-03 23:33:35 vedang"


(ns projclojure.sicp.ch1.notes)

;;; 1.2.4 Exponentiation

(defn expt
  "Recursive exponential function"
  [b n]
  (if (= n 0)
    1
    (* b (expt b (- n 1)))))


(defn expt-iter
  "Iterative exponential function"
  ([b n]
     (expt-iter b n 1))
  ([b n p]
     (if (= n 0)
       p
       (recur b (- n 1) (* p b)))))


(defn- square
  [n]
  (* n n))


(defn fast-expt
  "Exploit the following property of exponential:
b^n = (b^(n/2))^2 if n is even,
b^n = (b^(n-1))*b if n is odd"
  [b n]
  (cond
    (= n 0) 1
    (even? n) (square (fast-expt b (/ n 2)))
    :else (* b (fast-expt b (- n 1)))))
