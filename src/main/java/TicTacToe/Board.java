package TicTacToe;

import TicTacToe.winningStrategy.WinningStrategy;

import java.util.List;

public class Board {

    private final Cell[][] grid;
    private int movesCount;
    private final int size;
    List<WinningStrategy> winningStrategyList;

    public Board(int size, List<WinningStrategy> winningStrategyList){

        this.size = size;
        this.grid = new Cell[size][size];
        this.movesCount = 0;
        this.winningStrategyList = winningStrategyList;

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                grid[i][j] = new Cell();
            }
        }
    }

    public boolean isValidMove(int row, int col){
        if(row < 0 || col < 0 || row >= size || col>= size){
            return false;
        }
        return grid[row][col].isEmpty();
    }

    public void placeMove(int row, int col, Symbol symbol){
        if(!isValidMove(row,col)){
            throw new IllegalArgumentException("Invalid move at ("+row+","+col+")");
        }
        grid[row][col].setSymbol(symbol);
        movesCount++;
    }

    public boolean checkWin(Symbol symbol) {
        for (WinningStrategy strategy : winningStrategyList) {
            if (strategy.checkWinner(this, symbol)) {
                return true;
            }
        }
        return false;
    }


    public boolean isFull() {
        return movesCount == size * size;
    }

    public void reset() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j].setSymbol(Symbol.EMPTY);
            }
        }
        movesCount = 0;
    }


    public Symbol getSymbol(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Cell position out of bounds");
        }
        return grid[row][col].getSymbol();
    }

    public int getSize() {
        return size;
    }

    // Optional helper to print the board for CLI games
    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
    }
}