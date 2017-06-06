(ns tictactoe.core
  (:gen-class))

(defn new-board []
  [[nil nil nil] [nil nil nil] [nil nil nil]])

(defn get-columns [board]
  (apply mapv vector board))

(defn get-diagonals [board]
  (let [get-from-coordinates (fn [coor-vec] (get-in board coor-vec))]
    [(mapv get-from-coordinates [[0 0] [1 1] [2 2]])
     (mapv get-from-coordinates [[0 2] [1 1] [2 0]])]))

(defn check-board [board]
  (let [transpose-board
        (fn [board]
          (conj
            board
            (get-columns board)
            (get-diagonals board)))]
    (first
      (for [row (transpose-board board)
            :when (or
                    (every? #{:x} row)
                    (every? #{:o} row))
            :let [winning-mark (first row)]]
        winning-mark))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

