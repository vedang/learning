;;; ch1_19.clj --- fast fib function.
;;; After doing some math, we come to the following realization:
;;; 1. Tp,q(a,b)^n gives us the n+1th and nth Fib terms when:
;;;    p = 0, q = 1, a = 1, b = 0 and
;;;    T(a, b) = [(aq + bq + ap), (bp + aq)]
;;; 2. Tp,q(a,b)^2 = Tp',q'(a, b)
;;;    where p' = q^2 + p^2
;;;          q' = q^2 + 2pq
;;; Author: Vedang Manerikar
;;; Created on: 05 Jan 2012
;;; Time-stamp: "2012-01-05 23:19:33 vedang"


(ns projclojure.sicp.ch1.ch1_19)


(defn- p-dash
  [p q]
  (+ (* p p) (* q q)))


(defn- q-dash
  [p q]
  (+ (* q q) (* 2 p q)))


(defn- new_a
  [a b p q]
  (+ (* a q) (* b q) (* a p)))


(defn- new_b
  [a b p q]
  (+ (* b p) (* a q)))


(defn fast-fib
  "use the math derived above"
  ([n]
     (fast-fib 1 0 0 1 n))
  ([a b p q n]
     (cond
       (= n 0) b
       (even? n) (recur a b (p-dash p q) (q-dash p q) (/ n 2))
       :else (recur (new_a a b p q) (new_b a b p q) p q (- n 1)))))


(defn fib
  "just to compare"
  ([n]
     (fib 0 1 n))
  ([a b n]
     (cond
       (= n 0) a
       :else (recur b (+ a b) (- n 1)))))
