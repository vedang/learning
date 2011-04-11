(ns clj-test.scratch
  (:require [clojure.data.finger-tree :as ft]))


;;; Testing finger trees.
(def cdl (ft/counted-double-list 1 2 3 4 5 6 7 8 9 10))


;;; Assoc = Split + Concat
(def parts
  (let [[left _ right] (ft/ft-split-at cdl 2)]
    {:left left :right right}))


(ft/ft-concat (ft/conjr (:left parts) 12) (:right parts))


;;; remove and insert
(ft/ft-concat (:left parts) (:right parts))


(ft/ft-concat (into (:left parts) '(34 45 56)) (:right parts))


(def tree (ft/counted-double-list 12 23 34 45 56 67 78 89))


(def css (apply ft/counted-sorted-set '(a j i m e d a f k b c g h l)))


(get css 'e)
(nth css 4)


(def empty-cost-tree (ft/finger-tree (ft/meter :cost 0 +)))


(def ct (conj empty-cost-tree
              {:id :h :cost 5}
              {:id :i :cost 1}
              {:id :j :cost 2}
              {:id :k :cost 3}
              {:id :l :cost 4}))

(next (ft/split-tree (rest ct) #(> % 7)))
