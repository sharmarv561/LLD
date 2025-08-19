package VendingMachine;

import VendingMachine.Enums.Coin;
import VendingMachine.Enums.Note;


public class VendingMachineDemo {
    public static void main(String[] args) {

        VendingMachineSystem machine = VendingMachineSystem.getInstance();

        // Add Products to Inventory
        Product coke = new Product("C1", "Coke", 1.50, 10);
        Product pepsi = new Product("P1", "Pepsi", 1.25, 5);
        Product water = new Product("W1", "Water", 1.00, 8);

        machine.getInventory().addProduct(coke.getCode(), coke);
        machine.getInventory().addProduct(pepsi.getCode(), pepsi);
        machine.getInventory().addProduct(water.getCode(), water);

        // Simulate transaction
        System.out.println("\n--- User chooses Pepsi ---");
        machine.selectProduct("P1");

        System.out.println("\n--- User inserts a dollar note ---");
        machine.insertNote(Note.ONE);

        System.out.println("\n--- User inserts a quarter ---");
        machine.insertCoin(Coin.QUARTER);

        System.out.println("\n--- Dispensing product ---");
        machine.dispense();

        System.out.println("\n--- Returning remaining change (if any) ---");
        machine.returnChange();

        // Optional: try cancel flow
        System.out.println("\n--- New Transaction: Select Coke, then cancel ---");
        machine.selectProduct("C1");
        machine.insertNote(Note.ONE);
        machine.cancel();

    }
}
