;;; Peter Norvig's sudoku solver in clojure
;;; Ref: http://norvig.com/sudoku.html
;;; Also picked up some tricks from
;;; http://www.learningclojure.com/2009/11/sudoku_24.html
;;; I will also use this opportunity to learn how to write tests in clojure

(ns nuggets.sudoku
  (:require [clojure.set :as set]))

(def *default-grid* "4.....8.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......")

(def digits "123456789")
(def seperators "0.")

(def rows "ABCDEFGHI")
(def cols "123456789")

(defn cross
  "Cross-product of elements in A and elements in B"
  [A B]
  (for [a A
        b B]
    (str a b)))

(def squares (cross rows cols))

(def init-map
     ;; initial state of our final solution
     (apply merge
	    {}
	    (for [s squares]
	      {s digits})))

(def unitlist
     ;; a list of all the units in a sudoku puzzle
     ;; a unit is a set of squares inside which no digit
     ;; should be repeated.
     (map set
	  (concat
	   (for [c cols]
	     (cross rows [c]))
	   (for [r rows]
	     (cross [r] cols))
	   (for [rs (partition 3 rows)
		 cs (partition 3 cols)]
	     (cross rs cs)))))

(def units
     ;; A mapping of each sq to the units it belongs to.
     (reduce (fn [units-map [sq unit]]
               (assoc units-map
                 sq
                 (conj (get units-map sq [])
                       unit)))
             {}
             (for [s squares
                   u unitlist :when (u s)]
               [s u])))

(def peers
     ;; for each square, a list of squares which are it's peers
     (reduce (fn [peers-map [sq unit]]
               (merge-with set/union
                           peers-map
                           {sq unit}))
             {}
             (for [s squares
                   u unitlist :when (u s)]
               [s (disj u s)])))

(defn strip-input
  "Strip the input to create a proper grid"
  [input]
  (filter
   (set (concat digits seperators))
   input))

(defn grid-values
  "Convert grid into map with '.' or '0' for empty squares"
  [grid]
  (let [chars (strip-input grid)]
    (zipmap squares chars)))

(defn safe-merge
  "A helper function to ensure that merge does not crash"
  [& maps]
  (apply merge {} (remove false? maps)))

(defn call
  "All state changing functions should go through this.
I will try to bring about some semblance of state-control here using merge"
  [f & args]
  (safe-merge solution-map
	      (f args)))

;;; End of basic definitions and helper functions.
;;; Time to get down to the real thing

(declare parse-grid
	 assign
	 eliminate
	 propagate-peers
	 propagate-units)

(defn parse-grid
  "Take the initial unsolved sudoku grid and parse it into the solution map"
  ([]
     (parse-grid *default-grid*))
  ([grid]
     (let [init-values (grid-values grid)
	   solution-map init-map]
       (cond
	(empty? init-values) solution-map
	:else (try)))))

(defn assign
  "Assign a value d to the square sq in value-map.
This is equivalent to eliminating all other values from that square and
propagating the effect"
  [value-map sq d]
  ())
