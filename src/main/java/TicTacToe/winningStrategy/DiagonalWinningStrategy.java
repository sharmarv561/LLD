package TicTacToe.winningStrategy;

import TicTacToe.Board;
import TicTacToe.Symbol;

public class DiagonalWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Symbol symbol) {
        int size = board.getSize();

        // Check main diagonal
        boolean mainDiagonalWin = true;
        for (int i = 0; i < size; i++) {
            if (board.getSymbol(i, i) != symbol) {
                mainDiagonalWin = false;
                break;
            }
        }
        if (mainDiagonalWin) return true;

        // Check anti diagonal
        boolean antiDiagonalWin = true;
        for (int i = 0; i < size; i++) {
            if (board.getSymbol(i, size - 1 - i) != symbol) {
                antiDiagonalWin = false;
                break;
            }
        }
        return antiDiagonalWin;
    }
}
}
