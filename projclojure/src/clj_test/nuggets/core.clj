(ns nuggets.core)

;; Generate the fibonacci series:

;;; Sidhant:
(def fibs1 (lazy-cat [0 1] (map + fibs1 (rest fibs1))))

;;; BG:
(def fibs2 (map first (iterate (fn [[a b]] [b (+ a b)]) [0 1])))

;; Count the number of times a key repeats in a sequence

;;; Sidhant:
(into {} (map (fn [[x y]] [x (count y)]) (group-by identity [:a :a :b :b :b :c])))

;;; Nipra
(apply merge-with + (map (fn [x] {x 1}) [:a :a :b :b :b :c]))

;;; BG:
(reduce #(assoc %1 %2 (inc (%1 %2 0))) {} [:a :a :b :b :b :c])

;; The thrush combinator: piping the results of one operation to the next

;;; Elegant solution by @chrishouser
(defn thrush [& args]
  (reduce #(%2 %1) args))
