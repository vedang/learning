(ns clj-test.bg-classes)


(defn dropnth
  "Drop the nth term in the collection"
  [coll n]
  (mapcat (fn [xs]
            (if-not (= (count xs) n)
              xs
              (butlast xs))) (partition n n [] coll)))


(defn myflatten
  "Flatten stuff"
  ([coll]
     (myflatten coll []))
  ([coll acc]
     (cond
       (empty? coll) acc
       (not (coll? (first coll))) (recur (rest coll) (conj acc (first coll)))
       :else (recur (rest coll) (myflatten (first coll) acc)))))


(defn roman-to-arabic
  "Convert Roman to Arabic"
  ([rstr]
     (roman-to-arabic rstr 0))
  ([rstr acc]
     (let [rmap {\M 1000
                 \D 500
                 \C 100
                 \L 50
                 \X 10
                 \V 5
                 \I 1}]
       (cond
         (empty? rstr) acc
         (nil? (second rstr)) (+ acc (rmap (first rstr)))
         (< (rmap (first rstr))
            (rmap (second rstr))) (recur (rest rstr) (- acc
                                                        (rmap (first rstr))))
            :else (recur (rest rstr) (+ acc (rmap (first rstr))))))))


(defn get-val
  [a b]
  (let [rmap {\M 1000
              \D 500
              \C 100
              \L 50
              \X 10
              \V 5
              \I 1}]
    (if (and b (< (rmap a) (rmap b)))
      (- (rmap a))
      (rmap a))))


(defn better-rta
  "Dont use cond"
  ([rstr]
     (better-rta rstr 0))
  ([rstr acc]
     (apply + (map get-val rstr (concat (rest rstr) [nil])))))


(defn split-seqs
  [seqs minimum]
  (map (fn [s]
         (drop-while #(< % minimum) s)) seqs))


(defn lazy-search-2
  [oldmin seqs]
  (let [newmin (apply max (map first seqs))
        newseqs (split-seqs seqs newmin)]
    (if (= newmin oldmin)
      newmin
      (recur newmin newseqs))))


(defn lazy-search
  "forclosure prob 108"
  [& seqs]
  (let [minimum (apply max (map first seqs))
        new-seqs (split-seqs seqs minimum)]
    (lazy-search-2 minimum new-seqs)))


(defn seq-reduce
  "forclosure prob 60"
  ([fun coll]
     (lazy-seq (seq-reduce fun (first coll) (rest coll))))
  ([fun val coll]
     (if (seq coll)
       (let [newval (fun val (first coll))]
         (cons val (lazy-seq (seq-reduce fun
                                         newval
                                         (rest coll)))))
       (cons val nil))))


(defn pron
  [coll]
  (mapcat (juxt count first) (partition-by identity coll)))


(defn pronounciation
  "forclosure prob 110"
  [coll]
  (iterate pron (pron coll)))
