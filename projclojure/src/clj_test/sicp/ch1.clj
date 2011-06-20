;;; Structure and Interpretation of Computer Science
;;; - Abelson, Sussman
;;; Chapter 1
(ns clj-test.sicp.ch1)

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
