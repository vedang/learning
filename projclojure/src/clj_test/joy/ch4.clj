(ns clj-test.joy.ch4)
;;; The Joy of Clojure - Michael Fogus and Chris Houser.
;;; Chapter 4

;;; listing 4.1 linear lookup vs regular map lookup

;;; raw data
(defn raw
  [n]
  (map keyword
       (map str
            (map char
                 (range 97
                        (+ 97 n))))))

;;; linear model
(defn mk-lin
  [n]
  (interleave (raw n)
              (range n)))

;;; hash-map
(defn mk-map
  [n]
  (apply hash-map
         (mk-lin n)))

;;; linear lookup
(defn lhas
  [k s]
  (some #{k} s))

;;; map lookup
(defn mhas
  [k s]
  (s k))

(defn churn
  "Given a lookup function, a model maker and a number;
run the lookup on every element in a n-sized map"
  [lookup maker n]
  (let [ks (raw n)
        elems (maker n)]
    (dotimes [i 100000]
      (doseq [k ks]
        (lookup k elems)))))
