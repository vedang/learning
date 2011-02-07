(ns clj-test.joy.ch2)
;;; The Joy of Clojure - Michael Fogus and Chris Houser.
;;; Chapter 2

;;; listing 2.1
;;; A simple unix ls function

(defn ls
  "imitating unix ls"
  [path]
  (seq (.list (java.io.File. path))))

(defn build-path-results
  "call ls on all the arguments to this function"
  [command-line-args]
  (for [p command-line-args]
    (ls p)))

(defn print-file-details
  [file]
  (println (.getName file)))

(defn print-dir-details
  "for each file in listing, print it's details"
  [listing]
  (for [f listing]
    (print-file-details (java.io.File. f))))

;;; section 2.6.1
;;; Coloring every pixel of a canvas with the xor of it's x and y
;;; co-ordinates.

(defn xors
  "Print a tuple with the x-y co-ordinates and their xor"
  [xs ys]
  (for [x (range xs)
        y (range ys)]
    [x y (rem (bit-xor x y) 256)]))

(defn f-values
  "Print tuples for passed funtion"
  [f xs ys]
  (for [x (range xs)
        y (range ys)]
    [x y (rem (f x y) 256)]))

;;; Define a frame and grab it's graphics context
(def frame (java.awt.Frame.))
(.setVisible frame true)
(def gfx (.getGraphics frame))

;;; Finally, fill the frame
(defn frame-fill
  [f xs ys]
  (doseq [[x y v] (f-values f xs ys)]
    (.setColor gfx (java.awt.Color. v v v))
    (.fillRect gfx x y 1 1)))

;;; Try these out for fun
(comment
  (frame-fill bit-xor 500 500)
  (frame-fill bit-and 500 500)
  (frame-fill + 500 500)
  (frame-fill * 500 500)
  (frame-fill #(Math/abs (int (* 100 (Math/sin (* %1 %2))))) 500 500)
  (frame-fill #(Math/abs (int (* 100 (Math/tan (* %1 %2))))) 500 500))
