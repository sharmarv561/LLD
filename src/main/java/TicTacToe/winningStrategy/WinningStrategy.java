package TicTacToe.winningStrategy;

import TicTacToe.Board;
import TicTacToe.Symbol;

public interface WinningStrategy {
    boolean checkWinner(Board board, Symbol symbol);

}
