;; An implementation of quicksort.

(ns nuggets.qsort)

(defn qsort
  "this implementation is not lazy"
  [l]
  (if-let [l (seq l)]
    (let [[pivot & tail] l]
      (concat (qsort (filter #(>= pivot %)
                             tail))
              [pivot]
              (qsort (filter #(< pivot %)
                             tail))))
    []))

;;; Implementation from The Joy of Clojure

(defn nom
  "for generating random sequences"
  [n]
  (take n (repeatedly #(rand-int n))))

(defn cons-when
  "nice improvement over traditional cons"
  [v coll]
  (if (empty? v)
    coll
    (cons v coll)))

(defn sort-parts
  "the magic happens here"
  [work]
  (lazy-seq
   (loop [[part & parts :as work] work]
     (when work
       (if (coll? part)
         (let [[pivot & xs] part
               smaller #(< % pivot)]
           (recur (cons-when
                   (filter smaller xs)
                   (cons pivot
                         (cons-when
                          (remove smaller xs)
                          parts)))))
         (cons part (sort-parts parts)))))))

(defn qsort-lazy
  [xs]
  (sort-parts (list xs)))
