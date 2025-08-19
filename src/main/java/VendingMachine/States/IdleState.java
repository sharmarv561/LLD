package VendingMachine.States;

import VendingMachine.Enums.Coin;
import VendingMachine.Enums.Note;
import VendingMachine.Product;
import VendingMachine.VendingMachineSystem;

public class IdleState implements VendingMachineState{

    private final VendingMachineSystem machine;

    public IdleState(VendingMachineSystem machine) {
        this.machine = machine;
    }
    @Override
    public void selectProduct(String productCode) {
        if (!machine.getInventory().hasProduct(productCode)) {
            System.out.println("Product not found.");
            return;
        }

        machine.setSelectedProduct(machine.getInventory().getProduct(productCode));
        System.out.println("Product " + productCode + " selected.");
        machine.setState(new ReadyState(machine));
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please select a product before inserting coins.");
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Please select a product before inserting notes.");
    }

    @Override
    public void dispense() {
        System.out.println("No product selected.");
    }

    @Override
    public void returnChange() {
        System.out.println("No money to return.");
    }

    @Override
    public void cancel() {
        System.out.println("Nothing to cancel.");
    }
}
