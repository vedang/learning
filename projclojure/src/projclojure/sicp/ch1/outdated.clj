;;; Structure and Interpretation of Computer Science
;;; - Abelson, Sussman
;;; Chapter 1
(ns clj-test.sicp.ch1.outdated)

;;; Square root example from 1.1.7
(defn good-enough?
  "Is the guess good-enough?"
  [guess x]
  (< (Math/abs (- (* guess guess)
                  x))
     0.001))


(defn improve
  "Improve the guess"
  [guess x]
  (/ (+ guess
        (/ x guess))
     2))


(defn sqrt-iter
  "Newton's method"
  [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x) x)))


(defn sqrt
  "Find sqrt of x"
  [x]
  (sqrt-iter 1.0 x))


;;; Ackermann's function 1.10
(defn A
  "Ackermann's function"
  [x y]
  (cond
   (= y 0) 0
   (= x 0) (* 2 y)
   (= y 1) 2
   :else (A (- x 1)
            (A x (- y 1)))))


;;; Fibonacci using a linear iterative process.
(defn fib-iter
  "iteratively calculate the Fibonacci sequence"
  [a b n]
  (cond
    (= n 0) a
    :else (fib-iter b (+ a b) (- n 1))))


(defn fib
  "generate the nth Fibonacci number"
  [n]
  (fib-iter 0 1 n))


;;; counting change
(defn first-denomination
  "denomination of various kinds of coins"
  [kinds-of-coins]
  (cond
    (= kinds-of-coins 5) 50
    (= kinds-of-coins 4) 25
    (= kinds-of-coins 3) 10
    (= kinds-of-coins 2) 5
    (= kinds-of-coins 1) 1))


(defn cc
  [amount kinds-of-coins]
  (cond
    (= amount 0) 1
    (or (< amount 0)
        (= kinds-of-coins 0)) 1
        :else (+ (cc amount (- kinds-of-coins 1))
                 (cc (- amount
                        (first-denomination kinds-of-coins))
                     kinds-of-coins))))


(defn count-change
  "count the number of ways an amount can be paid using different denominations
of change"
  [amount]
  (cc amount 5))


;;; Exercise 1.11:
;;; f(n) = n if n < 3
;;; f(n) = f(n - 1) + 2f(n - 2) + 3f(n - 3) if n >= 3

(defn prob-1-11-recur
  "linear recursive process to solve Ex. 1.11"
  [n]
  (cond
    (< n 3) n
    :else (+ (prob-1-11-recur (- n 1))
             (prob-1-11-recur (- n 2))
             (prob-1-11-recur (- n 3)))))


(defn prob-1-11-iter
  "linear iterative process to solve Ex. 1.11"
  [a b c n]
  (cond
    (= n 2) c
    (= n 1) b
    (= n 0) a
    :else (prob-1-11-iter b c (+ a b c) (- n 1))))


(defn prob-1-11
  [n]
  (prob-1-11-iter 0 1 2 n))


;;; Exercise 1.12
;;; Write a procedure to compute elements of Pascal's Triangle.

(defn pascal-val
  "function to calculate value of element at a particular row and column
for the pascal triangle."
  [r c]
  (cond
    (= c 0) 1
    (= c r) 1
    :else (+ (pascal-val (- r 1) (- c 1))
             (pascal-val (- r 1) c)) ))


(defn pascal-row
  "calculate a row of the pascal triangle"
  [r]
  (map (fn [x]
         (pascal-val r x))
       (range (+ r 1))))


(defn pascal-triangle
  [i h]
  (println (pascal-row i))
  (cond
    (= h 0) nil
    :else (pascal-triangle (+ i 1) (- h 1))))


(defn print-pascal-triangle
  "print pascal triangle of height h"
  [h]
  (pascal-triangle 0 h))


;;; A better solution for pascal's triangle by BG
(defn next-row
  "Calculate the next row of Pascal's Triangle"
  [row]
  (vec (map + (concat [0] row) (concat row [0]))))


(defn pascal-triangle
  "infinite sequence of pascal rows"
  []
  (iterate next-row [1]))
