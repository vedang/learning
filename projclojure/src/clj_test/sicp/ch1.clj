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
