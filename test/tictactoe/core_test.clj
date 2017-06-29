(ns tictactoe.core-test
  (:require [clojure.test :refer :all]
            [tictactoe.core :refer :all]))

(deftest new-board-test
  (testing "build board takes args and returns 3 by 3 board"
    (let [input [1 2 3 4 5 6 7 8 9]
          result [[1 2 3] [4 5 6] [7 8 9]]]
      (is (= (build-board input) result))))

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
    (let [test-board (new-board)]
      (is (= (check-board test-board) nil)))

    (let [test-board [[:x nil nil] [:x :x :o] [:o nil nil]]]
      (is (= (check-board test-board) nil))))

  (testing "check board returns winner"
    (let [winning-board [[:x :x :x] [nil nil nil] [nil nil nil]]]
      (is (= (check-board winning-board) :x)))

    (let [winning-board [[nil nil nil] [:o :o :o] [nil nil nil]]]
      (is (= (check-board winning-board) :o))))

  (testing "check board also checks vertical"
    (let [winning-board [[:x nil nil] [:x nil nil] [:x nil nil]]]
      (is (= (check-board winning-board) :x))))

  (testing "check board also checks diagonals"
    (let [winning-board [[:o nil nil] [nil :o nil] [nil nil :o]]]
      (is (= (check-board winning-board) :o)))))

;prompt freezes while running test with lein.
(deftest terminal-interface-test
  #_(testing "prompt asks which square you wish to place move in"
      (is (= (with-out-str (prompt-for-move)) "Please input number of box you wish to mark:\n")))

  (testing "prompt reads input"
    (with-out-str (is (= "a" (with-in-str "a" (prompt-for-move))))))

  (testing "print board prints each row on a new line"
    (is 
      (=
       (with-out-str 
         (print-board [[:a :b :c] [:d :e :f]]))
       "[:a :b :c]\n[:d :e :f]\n"))))

(deftest place-mark-test
  (testing "place mark returns a new board with mark in position"
    (is (= 
          [[nil nil nil] [:x nil nil] [nil nil nil]]
          (place-mark (new-board) :x 3)))))
