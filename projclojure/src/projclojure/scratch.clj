(ns clj-test.scratch
  (:require [clojure.data.finger-tree :as ft]
            [clojure.contrib.monads :as m]
            [clojure.set :as s]
            [ring.adapter.jetty :as rj]))


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


;;; Jim Duey on Monads
(defn increase
  "Take a number and return the next two numbers"
  [x]
  (list (+ x 1) (+ x 2)))


(defn half-double
  "Half and double."
  [x]
  (list (/ x 2) (* x 2)))


;;; The Sequence Monad
(def m-result list)


(defn m-bind [v f]
  (mapcat f v))


;; (def new-monadic-function
;;   (m/m-chain [increase half-double]))


;;; The Set Monad
(defn increase-hs
  "Take a number and return the next two numbers"
  [x]
  (hash-set (+ x 1) (+ x 2)))


(defn half-double-hs
  "Half and double."
  [x]
  (hash-set (/ x 2) (* x 2)))


(def m-result-hs hash-set)


(defn m-bind-hs [v f]
  (apply s/union (map f v)))


;;; The state monad
(defn m-result-state [x]
  (fn [state]
    [x state]))


(defn m-bind-state [mv f]
  (fn [s]
    (let [[v ss] (mv s)]
      ((f v) ss))))


;;; An example with Ring.
(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Hello World from Ring"})
;;; (defonce server (rj/run-jetty #'my-app {:port 8080 :join? false}))


(def app1
  (m-result-state
   {:status 200
    :headers {"Content-Type" "text/html"}
    :body    "Hello World from Ring"}))
