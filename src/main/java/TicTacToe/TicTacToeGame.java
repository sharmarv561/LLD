package TicTacToe;

import TicTacToe.winningStrategy.ColumnWinningStrategy;
import TicTacToe.winningStrategy.DiagonalWinningStrategy;
import TicTacToe.winningStrategy.RowWinningStrategy;
import TicTacToe.winningStrategy.WinningStrategy;

import java.util.Arrays;
import java.util.List;

    public class TicTacToeGame {
        private final Board board;
        private final Player[] players;
        private int currentPlayerIndex;
        private GameStatus status;

        public TicTacToeGame(Player player1, Player player2, int size) {
            // Initialize winning strategies
            List<WinningStrategy> strategies = Arrays.asList(
                    new RowWinningStrategy(),
                    new ColumnWinningStrategy(),
                    new DiagonalWinningStrategy()
            );

            this.board = new Board(size, strategies);
            this.players = new Player[] { player1, player2 };
            this.currentPlayerIndex = 0; // player1 starts
            this.status = GameStatus.IN_PROGRESS;
        }

        public synchronized boolean playMove(int row, int col) {
            if (status != GameStatus.IN_PROGRESS) {
                System.out.println("Game has already ended.");
                return false;
            }

            Player currentPlayer = players[currentPlayerIndex];
            if (!board.isValidMove(row, col)) {
                System.out.println("Invalid move! Try again.");
                return false;
            }

            board.placeMove(row, col, currentPlayer.getSymbol());

            // Check win
            if (board.checkWin(currentPlayer.getSymbol())) {
                status = GameStatus.WIN;
                System.out.println(currentPlayer.getName() + " wins!");
            } else if (board.isFull()) {
                status = GameStatus.DRAW;
                System.out.println("Game ended in a draw.");
            } else {
                switchTurn();
            }
            return true;
        }

        private void switchTurn() {
            currentPlayerIndex = 1 - currentPlayerIndex; // toggles between 0 and 1
        }

        public synchronized void reset() {
            board.reset();
            currentPlayerIndex = 0;
            status = GameStatus.IN_PROGRESS;
        }

        public GameStatus getStatus() {
            return status;
        }

        public Player getCurrentPlayer() {
            return players[currentPlayerIndex];
        }

        public void printBoard() {
            board.print();
        }
    }
