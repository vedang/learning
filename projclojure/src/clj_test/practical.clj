(ns clj-test.practical)
;;Practical Clojure

(defn abs
  "calculate absolute value of a number"
  [n]
  (if (< n 0)
    (* -1 n)
    n))

(defn avg
  "Return the average of two numbers"
  [a b]
  (/ (+ a b) 2))

(defn good-enough?
  "Return true if guess is a good enough sq root of n"
  [guess n]
  (let [diff (- (* guess guess) n)]
    (if (< (abs diff) 0.001)
      true
      false)))

(defn sqrt
  "return square root of n"
  ([n]
     (sqrt n 1.0))
  ([n guess]
     (if (good-enough? guess n)
       guess
       (recur n (avg guess (/ n guess))))))

(defn loop-sqrt
  "Find sqrt using loop"
  [n]
  (loop [guess 1.0]
    (if (good-enough? guess n)
      guess
      (recur (avg guess (/ n guess))))))

(defstruct person :first_name :middle_name :last_name)

(def person1 (struct-map person :first_name "Vedang" :last_name "Manerikar"))

(def person2 (struct-map person :first_name "Praj" :last_name "Bhopale"))

(def get-first-name (accessor person :first_name))

(def my-contacts (ref []))

(defn add-contact
  "Add a contact to my contacts"
  [contacts contact]
  (dosync
   (alter contacts conj (ref contact))))

(defn print-contacts
  "print my contact list"
  []
  (doseq [c @my-contacts]
    (println (str "Name: " (@c :fname) ", Surname: " (@c :lname)))))

(add-contact my-contacts {:fname "Vedang" :lname "Manerikar"})

(def my-ref (ref 5))

(set-validator! my-ref (fn [x] (< 0 x)))

(defn my-watch
  [key identity old-val new-val]
  (println str "Old val: " old-val)
  (println str "New val: " new-val))

(add-watch my-ref :w1 my-watch)

(defmulti move :species)

(defmethod move ::elf [creature]
           (str (:name creature) " runs swiftly"))

(defmethod move ::human [creature]
           (str (:name creature) " walks steadily"))

(defmethod move ::orc [creature]
           (str (:name creature) " stomps heavily"))

(def a {:name "vedang" :species ::elf :strength 8})

(def b {:name "prajwalit" :species ::orc :strength 9})

(def c {:name "sidhant" :species ::human :strength 6})

(defmulti encounter (fn [x y]
                      [(:species x) (:species y)]))

(defmethod encounter [::elf ::elf] [elf1 elf2]
           (str "Two elves, " (:name elf1) (:name elf2) ", greet each other"))

(defmethod encounter [::elf ::orc] [elf orc]
           (str "Agile elf " (:name elf) " crushes the stupid orc " (:name orc)))

(defmethod encounter :default [x y]
           (str (:name x) " and " (:name y) " ignore each other and move on"))

(derive ::orc ::evil)
(derive ::human ::good)
(derive ::elf ::good)
(derive ::orc ::magical)
(derive ::elf ::magical)

(defmulti cast-spell :species)

(defmethod cast-spell ::magical [creature]
           (str "Thunder and magic! " (:name creature) " has cast a spell!"))

(defmethod cast-spell :default [creature]
           (str "No, " (:name creature) " is not magical and cannot cast a spell!"))

(defmulti slay :species)

(defmethod slay ::good [creature]
           (str "No! A good creature (" (:name creature) ") was slain"))

(defmethod slay ::magical [creature]
           (str "A magical creature (" (:name creature) ") was slain"))

(prefer-method slay ::good ::magical)

(defn make-heavy
  "Return a heavy version of a function"
  [f]
  (fn [& args]
    (Thread/sleep 1000)
    (apply f args)))

(defmacro triple-do [form]
  (list 'do form form form))

;; to evaluate something like (+ 5 (* 4 (debug-println (/ 4 3))))
(defmacro debug-println
  "Print and return result of expr"
  [expr]
  `(let [result# ~expr]
     (println (str "Value is: " result#))
     result#))

(defmacro rand-expr-multi
  "Take multiple forms as input and execute only one"
  [& forms]
  `(let [ct# ~(count forms)]
     (case (rand-int ct#)
           ~@(interleave (range (count forms)) forms))))

(defmacro ++
  [& exprs]
  (if (<= (count exprs) 2)
    `(+ ~@exprs)
    `(+ ~(first exprs) (++ ~@(rest exprs)))))

(defprotocol Employee
  (execute-order [this]))

(defrecord Drone [name dept]
  Employee
  (execute-order [this]
                 (println "I'm making tea")))

(def aDrone (Drone. "crazytwism" "cs"))

