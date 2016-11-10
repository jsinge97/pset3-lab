(ns pset3.core-test
  (:require [clojure.test :refer :all]
            [pset3.core :refer :all]))

(deftest unless
  (testing "unless macro"
    (is (= (unless (> 10 5)
                   "smaller"
                   "greater") "greater"))))

(deftest unless
  (testing "unless macro"
    (is (= (unless (> 10 20)
             "smaller"
             "greater") "smaller"))))

(deftest from
  (testing "from"
    (is (= (from n in names
                 (where (= (count n) 5))
                 (orderby n)
                 (select (.toUpperCase n)))
           '("BURKE" "DAVID" "FRANK")))))


(deftest with-file
  (testing "with-file"
    (is (= (with-file "project.clj"
             (if (.hasNextLine file)
               (.nextLine file)))
           "(defproject pset3 \"0.1.0-SNAPSHOT\""))))

(deftest nif
  (testing "nif"
    (is (= (nif -1
                "positive"
                "zero"
                "negative"))
        "negative")))

(deftest nif
  (testing "nif"
    (is (let [res (java.util.Scanner. (java.io.FileInputStream. "project.clj"))]
          (do (nif 0
                   "positive"
                   (prn (.nextLine res))
                   (prn "negative"))
              (.close res)))
        nil)))

