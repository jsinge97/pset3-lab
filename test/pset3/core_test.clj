(ns pset3.core-test
  (:require [clojure.test :refer :all]
            [pset3.core :refer :all]))

(deftest unless-test-1
  (testing "unless macro"
    (is (= (unless (> 10 5)
                   "smaller"
                   "greater") "greater"))))

(deftest unless-test-2
  (testing "unless macro"
    (is (= (unless (> 10 20)
             "smaller"
             "greater") "smaller"))))

(deftest from-test
  (testing "from"
    (is (= (from n in names
                 (where (= (count n) 5))
                 (orderby n)
                 (select (.toUpperCase n)))
           '("BURKE" "DAVID" "FRANK")))))


(deftest with-file-test
  (testing "with-file"
    (is (= (with-file "project.clj"
             (if (.hasNextLine file)
               (.nextLine file)))
           "(defproject pset3 \"0.1.0-SNAPSHOT\""))))

(deftest nif-test-1
  (testing "nif"
    (is (= (nif -1
                "positive"
                "zero"
                "negative"))
        "negative")))

(deftest nif-test-2
  (testing "nif"
    (is (let [res (java.util.Scanner. (java.io.FileInputStream. "project.clj"))]
          (do (nif 0
                   "positive"
                   (prn (.nextLine res))
                   (prn "negative"))
              (.close res)))
        nil)))

