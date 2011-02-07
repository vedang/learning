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
