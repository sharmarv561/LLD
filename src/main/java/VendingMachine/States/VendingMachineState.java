package VendingMachine.States;

import VendingMachine.Enums.Coin;
import VendingMachine.Enums.Note;
import VendingMachine.VendingMachineSystem;

public interface VendingMachineState {
    void selectProduct( String code);
    void insertCoin(Coin coin);
    void insertNote(Note note);
    void returnChange();
    void dispense();
    void cancel(); // added for better user control
}
