(ns clj-test.core)
;; ^{:doc "Code from The Little Schemer. This book is a classic and should be read by everyone"
;;   :author "Vedang Manerikar <vedang@infinitelybeta.com>"}

;;The little Schemer

(defn atom? [x]
  (not (seq? x)))

(defn lat? [l]
  (cond
   (atom? l) false
   (empty? l) true
   (atom? (first l)) (recur (rest l))
   :else false))

(defn recur-member? [a lat]
  (cond
   (empty? lat) false
   :else (or (= (first lat) a) (recur-member? a (rest lat)))))

(defn member? [a lat]
  (cond
   (not (atom? a)) false
   (not (lat? lat)) false
   :else (recur-member? a lat)))

(defn rember
  "remove the first occurrence of a from lat"
  [a lat]
  (cond
   (empty? lat) ()
   :else (cond
          (= a (first lat)) (rest lat)
          :else (conj (rember a (rest lat)) (first lat)))))

(defn firsts [l]
  (cond
   (empty? l) ()
   :else (conj (firsts (rest l)) (first (first l)))))

(defn insertR [new old lat]
  (cond
   (empty? lat) ()
   :else (cond
          (= old (first lat)) (conj (conj (rest lat) new) old)
          :else (conj (insertR new old (rest lat)) (first lat)))))

(defn insertL [new old lat]
  (cond
   (empty? lat) ()
   :else (cond
          (= old (first lat)) (conj lat new)
          :else (conj (insertL new old (rest lat)) (first lat)))))

(defn subst
  "Substitute old by new in lat"
  [new old lat]
  (cond
   (empty? lat) ()
   :else (cond
          (= old (first lat)) (conj (rest lat) new)
          :else (conj (subst new old (rest lat)) (first lat)))))

(defn subst2
  "substitute first occurrence of o1 or o2 by lat"
  [new o1 o2 lat]
  (cond
   (empty? lat) ()
   :else (cond
          (or (= o1 (first lat)) (= o2 (first lat))) (conj (rest lat) new)
          :else (conj (subst2 new o1 o2 (rest lat)) (first lat)))))

(defn multirember
  "Remove all occurrences of a from lat"
  [a lat]
  (cond
   (empty? lat) ()
   :else (cond
          (= a (first lat)) (recur a (rest lat))
          :else (conj (multirember a (rest lat)) (first lat)))))

(defn multiinsertR
  "Insert new to right of all occurrences of old in lat"
  [new old lat]
  (cond
   (empty? lat) ()
   :else (cond
          (= old (first lat)) (conj (conj (multiinsertR new old (rest lat)) new) old)
          :else (conj (multiinsertR new old (rest lat)) (first lat)))))

(defn multiinsertL
  "Insert new to the left of all occurrences of old in lat"
  [new old lat]
  (cond
   (empty? lat) ()
   :else (cond
          (= old (first lat)) (conj (conj (multiinsertL new old (rest lat))
                                          old)
                                    new)
          :else (conj (multiinsertL new old (rest lat)) (first lat)))))

(defn tup+
  "Add each element of two tups and return new tup"
  [tup1 tup2]
  (cond
   (empty? tup1) tup2
   (empty? tup2) tup1
   :else (conj (tup+ (rest tup1) (rest tup2))
               (+ (first tup1) (first tup2)))))

(defn pick
  "pick the nth element in a lat"
  [n lat]
  (cond
   (zero? (dec n)) (first lat)
   :else (pick (dec n) (rest lat))))

(defn rempick
  "remove the nth element in the lat"
  [n lat]
  (cond
   (zero? (dec n)) (rest lat)
   :else (conj (rempick (dec n) (rest lat))
               (first lat))))

(defn no-nums
  "remove all numbers from a lat"
  [lat]
  (cond
   (empty? lat) ()
   :else (cond
          (number? (first lat)) (no-nums (rest lat))
          :else (conj (no-nums (rest lat))
                      (first lat)))))

(defn rember*
  "star makes me think 'oh my gawd'"
  [a l]
  (cond
   (empty? l) ()
   (atom? (first l)) (cond
                      (= a (first l)) (rember* a (rest l))
                      :else (conj (rember* a (rest l))
                                  (first l)))
   (lat? (first l)) (conj (rember* a (rest l))
                          (rember a (first l)))
   :else (conj (rember* a (rest l))
               (rember* a (first l)))))

(defn insertR*
  "star version of insertR"
  [new old l]
  (cond
   (empty? l) ()
   (atom? (first l)) (cond
                      (= old (first l)) (conj (conj (insertR* new old (rest l))
                                                    new)
                                              old)
                      :else (conj (insertR* new old (rest l)) (first l)))
   (lat? (first l)) (conj (insertR* new old (rest l))
                          (insertR new old (first l)))
   :else (conj (insertR* new old (rest l))
               (insertR* new old (first l)))))

(defn leftmost
  "Find the leftmost atom in l"
  [l]
  (cond
   (empty? l) ()
   (atom? (first l)) (first l)
   :else (leftmost (first l))))

(defn numbered?
  "Is given s-exp arithmetic?"
  [aexp]
  (cond
   (atom? aexp) (number? aexp)
   :else (and (numbered? (first aexp))
              (numbered? (rest (rest aexp))))))

(defn myset?
  "no item should appear more than once"
  [lat]
  (cond
   (empty? lat) true
   (member? (first lat) (rest lat)) false
   :else (recur (rest lat))))

(defn subset?
  "Is set1 a subset of set2?"
  [set1 set2]
  (cond
   (empty? set1) true
   (member? (first set1) set2) (recur (rest set1) set2)
   :else false))

(defn intersect
  "print intersect of two sets"
  [set1 set2]
  (cond
   (empty? set1) ()
   (member? (first set1) set2) (conj (intersect (rest set1) set2)
                                     (first set1))
   :else (recur (rest set1) set2)))

(defn union
  "print union of two sets"
  [set1 set2]
  (cond
   (empty? set1) set2
   (member? (first set1) set2) (recur (rest set1) set2)
   :else (conj (union (rest set1) set2)
               (first set1))))

(defn rember-f
  "uses the test function to test for a in l and remove the first occurrence"
  [test? a l]
  (cond
   (empty? l) ()
   (test? (first l) a) (rest l)
   :else (conj (rember-f test? a (rest l))
               (first l))))


(defn eq?-c
  [a]
  (fn [x]
     (= x a)))


(defn eq?-salad
  []
  (eq?-c 'salad))


(defn rember-f
  "takes a test function and returns a function to do rember"
  [test?]
  (fn [a l]
    (cond
     (empty? l) ()
     (test? (first l) a) (rest l)
     :else (conj ((rember-f test?) a (rest l))
                 (first l)))))


(defn insertL-f
  "Return a function to do insertL"
  [test?]
  (fn [new old l]
    (cond
     (empty? l) ()
     (test? old (first l)) (conj (conj ((insertL-f test?) new old (rest l))
                                       old)
                                 new)
     :else (conj ((insertL-f test?) new old (rest l)) (first l)))))


(defn insertR-f
  "Return a function to do insertR"
  [test?]
  (fn [new old l]
    (cond
     (empty? l) ()
     (test? old (first l)) (conj (conj ((insertR-f test?) new old (rest l))
                                       new)
                                 old)
     :else (conj ((insertR-f test?) new old (rest l)) (first l)))))


(defn seqL
  "cons new to left of old"
  [new old l]
  (conj (conj l old) new))


(defn seqR
  "cons new to the right of old"
  [new old l]
  (conj (conj l new) old))


(defn seqS
  "subst"
  [new old l]
  (conj l new))


(defn seqrem
  "remove"
  [new old l]
  l)


(defn insert-g
  "return insertL or insertR"
  [seq]
  (fn [new old l]
    (cond
     (empty? l) ()
     (= old (first l)) (seq new old (rest l))
     :else (conj ((insert-g seq) new old (rest l)) (first l)))))


(def insertL (insert-g seqL))
(def insertR (insert-g seqR))
(def subst (insert-g seqS))
(def rember (fn [a l]
              ((insert-g seqrem) false a l)))


(defn a-friend
  [x y]
  (empty? y))


(defn last-friend
  [x y]
  (count x))

(defn multirember&co
  "introduction to continuations"
  [a lat col]
  (cond
   (empty? lat) (col () ())
   (= a (first lat)) (multirember&co a (rest lat) (fn [newlat seen]
                                                    (col newlat (conj seen (first lat)))))
   :else (multirember&co a (rest lat) (fn [newlat seen]
                                        (col (conj newlat (first lat)) seen)))))

(defn multiinsertLR
  [new oldL oldR lat]
  (cond
   (empty? lat) ()
   (= oldL (first lat)) (conj (conj (multiinsertLR new oldL oldR (rest lat)) oldL) new)
   (= oldR (first lat)) (conj (conj (multiinsertLR new oldL oldR (rest lat)) new) oldR)
   :else (conj (multiinsertLR new oldL oldR (rest lat)) (first lat))))

(defn lrFriend
  [lat l r]
  (conj (conj (conj (conj lat 'L) l) 'R) r))

(defn multiinsertLR&co
  [new oldL oldR lat col]
  (cond
   (empty? lat) (col () 0 0)
   (= oldL (first lat)) (multiinsertLR&co new oldL oldR (rest lat)
                                          (fn [newlat L R]
                                            (col (conj (conj newlat oldL) new)
                                                 (inc L) R)))
   (= oldR (first lat)) (multiinsertLR&co new oldL oldR (rest lat)
                                          (fn [newlat L R]
                                            (col (conj (conj newlat new) oldR)
                                                 L (inc R))))
   :else (multiinsertLR&co new oldL oldR (rest lat)
                           (fn [newlat L R]
                             (col (conj newlat (first lat)) L R)))))

(defn keep-looking
  [a n lat]
  (cond
   (number? n) (keep-looking a (pick n lat) lat)
   :else (= a n)))

(defn looking
  "positions are indexes"
  [a lat]
  (keep-looking a (pick 1 lat) lat))
