;;; a program to solve the Spellathon word puzzle in Pune Times
;;; Basically, you are given 7 letters, 1 of which is *compulsory*
;;; You have to print all the four-letter or above words that can
;;; be formed using any of the 6 letters and the *compulsory* letter
;;; (that is, the *compulsory* letter must always be used)

;;; Eg: *W* E E A R D L
;;; WEED, WADE, WARD, AWED, DRAW, DREW, DRAWL, LEWD, LEEWARD etc

(ns nuggets.words
  (:require [clojure.contrib.combinatorics :as ccc]
            [clojure.contrib.duck-streams :as streams]
            [clojure.contrib.str-utils :as str-utils]))

(defn tokenize
  "Simple tokenization of a block of text."
  [text]
  (str-utils/re-split #"[\s]+" (str-utils/re-gsub #"[^a-z\s]+" "" (.toLowerCase text))))

(def *dict* (set (tokenize (streams/slurp* "/usr/share/dict/words"))))

(defn get-subset
  "Get every unique subset of our letters such that length of the
subset is greater than 3 and it contains the *compulsory* letter"
  [*compulsory* items]
  (set (filter #(some #{*compulsory*} %)
               (filter #(> (count %) 3) (ccc/subsets (concat [*compulsory*] items))))))

(defn make-word
  "Take a list of characters and make a word by stringing them together"
  [l]
  (.toLowerCase (apply str l)))

(defn solve-puzzle
  "Solve the puzzle"
  [[*compulsory* & items]]
  (set (filter *dict*
               (map make-word
                    (mapcat ccc/permutations
                            (get-subset *compulsory* items))))))

;;; Usage
(comment
  (solve-puzzle ["W" "E" "E" "L" "A" "R" "D"])
  ;; =>
  #{"drew" "were" "waled" "wald" "wale" "draw" "weld" "lewd" "wader" "dewar" "leeward" "drawl" "wade" "weed" "lewder" "welder" "awed" "weal" "weer" "ward" "ware" "ewer" "wear"})
