;;; onida.clj --- code to show movement of the onida marker on screen
;;; Author: Vedang Manerikar
;;; Created on: 21 Nov 2011
;;; Time-stamp: "2011-11-22 00:07:03 vedang"


(ns clj-test.nuggets.onida)


(def *rebound-vec* [0 1 2 3 4 5 6 7 8 9 10])

;;; Define a frame and grab it's graphics context
;; (def frame (java.awt.Frame. "ONIDA TV"))
;; (.setVisible frame true)
;; (def gfx (.getGraphics frame))


(defn f-values
  "Print tuples for passed funtion"
  [f xs ys]
  (for [x (range xs)
        y (range ys)]
    [x y (rem (f x y) 256)]))


(defn frame-fill
  "Fill a predefined frame with our function"
  [f xs ys gfx]
  (doseq [[x y v] (f-values f xs ys)]
    (.setColor gfx (java.awt.Color. v v v))
    (.fillRect gfx x y 1 1)))


(defn main
  "Entry point"
  [tv-dim x1 y1 x2 y2]
  )
