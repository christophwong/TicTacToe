(ns tictactoe.core-test
  (:require [clojure.test :refer :all]
            [tictactoe.core :refer :all]))

(deftest new-board-test
  (testing "new board creates empty 3 by 3 board"
    (let [test-board [[nil nil nil] [nil nil nil] [nil nil nil]]]
      (is (= (new-board) test-board)))))

(deftest transpose-board-test
  (testing "getting columns from board"
    (let [test-board [[:x :o :x] [:x :o :x] [:x :o :x]]
          result [[:x :x :x] [:o :o :o] [:x :x :x]]]
      (is (= (get-columns test-board) result))))

  (testing "getting diagonals from board"
    (let [test-board [[:x nil :o] [nil :x nil] [:o nil :x]]
          result [[:x :x :x] [:o :x :o]]]
      (is (= (get-diagonals test-board) result)))))

(deftest board-check-test
  (testing "check board returns nil if no winner"
    (let [test-board [[nil nil nil] [nil nil nil] [nil nil nil]]]
      (is (= (check-board test-board) nil)))

    (let [test-board [[:x nil nil] [:x :x :o] [:o nil nil]]]
      (is (= (check-board test-board) nil))))

  (testing "check board returns winner"
    (let [winning-board [[:x :x :x] [nil nil nil] [nil nil nil]]]
      (is (= (check-board winning-board) :x)))

    (let [winning-board [[nil nil nil] [:o :o :o] [nil nil nil]]]
      (is (= (check-board winning-board) :o))))

  (testing "check board also checks vertical and diagonal winners"
    (let [winning-board [[:x nil nil] [:x nil nil] [:x nil nil]]]
      (is (= (check-board winning-board) :x)))

    (let [winning-board [[:o nil nil] [nil :o nil] [nil nil :o]]]
      (is (= (check-board winning-board) :o)))))
