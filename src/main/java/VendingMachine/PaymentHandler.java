package VendingMachine;

import VendingMachine.Enums.Coin;
import VendingMachine.Enums.Note;

import java.util.HashMap;
import java.util.Map;

public class PaymentHandler {
    private double currentBalance = 0.0;
    private final Map<Coin, Integer> insertedCoins = new HashMap<>();
    private final Map<Note, Integer> insertedNotes = new HashMap<>();

    public void insertCoins(Coin coin){
        insertedCoins.put(coin, insertedCoins.getOrDefault(coin, 0) + 1);
        System.out.println("Inserted coin: $" + coin.getValue());
    }

    public void insertNoted(Note note){
        insertedNotes.put(note, insertedNotes.getOrDefault(note, 0) + 1);
        System.out.println("Inserted coin: $" + note.getValue());
    }

    public void reset() {
        insertedCoins.clear();
        insertedNotes.clear();
    }

    public Map<Coin, Integer> getInsertedCoins() {
        return new HashMap<>(insertedCoins);
    }

    public Map<Note, Integer> getInsertedNotes() {
        return new HashMap<>(insertedNotes);
    }

    public double returnChange(double balance) {
        reset();  // resets internal coin/note state if needed
        return balance;
    }



}
