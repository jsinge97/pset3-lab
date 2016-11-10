(ns pset3.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;; source: https://github.com/leonardoborges/clojure-macros-workshop

;; ==== p1: unless ==== ;;
(defmacro unless [& args])

;; ==== p2: LINQ inspired API ==== ;;
;; Write a macro 'from':

(def names ["Burke", "Connor", "Frank", "Everett", "Albert", "George", "Harris", "David"])

(defmacro from [& args])


;; ==== p3: Write a macro 'with-file': ==== ;;
;; Allows us to read the contents of a file and have the file
;; be automatically closed at the end.

(defmacro with-file [& args])

;; ==== p4: macro debugging ==== ;;
;; The macro below has a bug. Can you spot it?

(defmacro square [n]
  `(* ~n ~n))

;; (let [seed (atom 9)
;;       next (fn [] (swap! seed inc))]
;;   (square (next))) => 100

;; ==== p5: moar debugging ==== ;;
;; Given the following macro:
(defmacro nif [val pos zero neg]
  "Numeric if. Executes pos, zero or neg depending
  if val is positive, zero or negative respectively"
  `(let [~'res ~val]
     (cond (pos? ~'res) ~pos
           (zero? ~'res) ~zero
           :else ~neg)))

;;The fact below HOLDS true as it is:
;; (nif -1
;;      "positive"
;;      "zero"
;;      "negative") => "negative"

;; However this next fact doesn't. Can you spot the bug?
;; Now fix it.
;; (let [res (java.util.Scanner. (java.io.FileInputStream. "project.clj"))]
;;   (do (nif 0
;;            "positive"
;;            (prn (.nextLine res))
;;            (prn "negative"))
;;       (.close res))) => nil

;; ==== BONUS (not harder, just has the word "monad" in it) ==== ;;

;; Monad comprehension with the list monad

;; We first define the list monad
(def list-monad
  {:return (fn [v] [v])
   :bind (fn [mv f]
           (if (seq mv)
             (apply concat (map f mv))
             []))})

;; As per the monad above, the fact below holds true:
;; (let [bind (:bind list-monad)
;;      return (:return list-monad)]
;;  (-> [1 2]
;;      (bind (fn [a]
;;              (-> [a (- a)]
;;                  (bind (fn [b]
;;                          (return (* 3 b))))))))) => '(3 -3 6 -6)

;; Write a macro 'domonad':
(defmacro domonad [& args])

;; That allows for a nicer monad comprehension syntax and makes the
;;fact below hold true:
;; (domonad list-monad
;;    [a [1 2]
;;     b [a, (- a)]]
;;     (* 3 b)) => '(3 -3 6 -6)
