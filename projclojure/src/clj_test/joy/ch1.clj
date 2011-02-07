(ns clj-test.joy.ch1)
;;; The Joy of Clojure - Michael Fogus and Chris Houser.
;;; Chapter 1

;;; listing 1.2
(def AND #(and %1 %2))
(def rank (zipmap [- + * / AND =] (iterate inc 1))) ;; order of precedence

(defn infix*
  "oh my gawd, it's full of stars"
  [[a b & [c d e & more]]]
  (cond
   (vector? a) (recur (list* (infix* a) b c d e more))
   (vector? c) (recur (list* a b (infix* c) d e more))
   (ifn? b) (if (and d (< (rank b 0) (rank d 0)))
              (recur (list a b (infix* (list* c d e more))))
              (recur (list* (b a c) d e more)))
   :else a))

(defn infix [& args]
  (infix* args))

;;; listing 1.3
;;; A simple chess-board representation
(defn initial-board []
  [\r \n \b \q \k \b \n \r
   \p \p \p \p \p \p \p \p
   \- \- \- \- \- \- \- \-
   \- \- \- \- \- \- \- \-
   \- \- \- \- \- \- \- \-
   \- \- \- \- \- \- \- \-
   \P \P \P \P \P \P \P \P
   \R \N \B \Q \K \B \N \R])

(def *file-key* \a)
(def *rank-key* \0)

(defn- file-component
  "Calculate the horizontal position"
  [file]
  (- (int file) (int *file-key*)))

(defn- rank-component
  "Calculate the vertical position"
  [rank]
  (* 8 (- 8 (- (int rank) (int *rank-key*)))))

(defn- index
  "Return index to address the appropriate square"
  [file rank]
  (+ (file-component file) (rank-component rank)))

(defn lookup
  "Lookup the piece at a given position on a given board.
Eg: (lookup (initial-board) \"a8\")"
  [board pos]
  (let [[file rank] pos]
    (board (index file rank))))

;;; listing 1.6
(defn lookup2
  "an encapsulated lookup function"
  [board pos]
  (let [[file rank] pos
        fc (- (int file) (int \a))
        rc (* 8 (- 8 (- (int rank) (int \0))))
        index (+ fc rc)]
    (board index)))
