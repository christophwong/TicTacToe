(ns tictactoe.core
  (:gen-class))

(defn build-board [li]
  (partition 3 li))

(defn new-board []
  (build-board (repeat 9 nil)))

(defn get-columns [board]
  (apply mapv vector board))

(defn get-diagonals [board]
  (let [get-from-coordinates (fn [coor-vec] (get-in board coor-vec))]
    [(mapv get-from-coordinates [[0 0] [1 1] [2 2]])
     (mapv get-from-coordinates [[0 2] [1 1] [2 0]])]))

(defn check-row [board]
  (first
    (for [row board
          :when (or
                  (every? #{:x} row)
                  (every? #{:o} row))
          :let [winning-mark (first row)]]
      winning-mark)))

(defn check-board [board]
  (or
    (check-row board)
    (check-row (get-columns board))
    (check-row (get-diagonals board))))

(defn prompt-for-move []
 (println  "Please input number of box you wish to mark:")
 ;takes input and returns formatted input
 (let [input (read-line)]
   input))

(defn place-mark [board mark position]
  (build-board (assoc (vec (flatten (new-board))) position mark)))

(defn print-board [board]
  (doseq [row board]
    (println row)))

(defn start-game []
  (print-board (new-board))
  (prompt-for-move))

(defn -main
  [& args]
  (start-game))

