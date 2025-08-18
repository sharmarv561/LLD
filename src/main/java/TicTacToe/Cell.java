package TicTacToe;

public class Cell {
    private Symbol symbol;

    public Cell(){
        this.symbol = Symbol.EMPTY;
    }

    public Symbol getSymbol(){
        return symbol;
    }

    public void setSymbol(Symbol symbol){
        this.symbol = symbol;
    }

    public boolean isEmpty(){
        return this.symbol == Symbol.EMPTY;
    }

    /* If symbol were public:

    Any class could freely change it, possibly breaking the game rules.

    It’s harder to track or prevent incorrect state changes.

    Violates the Encapsulation principle, which leads to fragile and hard-to-maintain code. */
}
