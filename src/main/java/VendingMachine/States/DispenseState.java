package VendingMachine.States;

import VendingMachine.Enums.Coin;
import VendingMachine.Enums.Note;
import VendingMachine.Product;
import VendingMachine.VendingMachineSystem;

public class DispenseState implements VendingMachineState{
    private final VendingMachineSystem machine;

    public DispenseState(VendingMachineSystem machine) {
        this.machine = machine;
    }
    @Override
    public void selectProduct(String code) {
        throw new IllegalStateException("Dispensing in progress. Please wait.");
    }

    @Override
    public void insertCoin(Coin coin) {
        throw new IllegalStateException("Dispensing in progress. Please wait.");
    }

    @Override
    public void insertNote(Note note) {
        throw new IllegalStateException("Dispensing in progress. Please wait.");
    }

    @Override
    public void returnChange() {
        System.out.println("Dispensing in progress, cannot return change.");
    }

    @Override
    public void dispense() {
        Product product = machine.getSelectedProduct();

        if (product == null) {
            throw new IllegalStateException("No product selected.");
        }

        if (!machine.getInventory().isAvailable(product.getCode())) {
            System.out.println("Product out of stock.");
            machine.resetTransaction();
            machine.setState(new IdleState(machine));
            return;
        }

        System.out.println("Dispensing: " + product.getName());

        double price = product.getPrice();
        double balance = machine.getCurrentBalance();

        if (balance < price) {
            System.out.println("Insufficient balance.");
            return;
        }
        machine.getInventory().reduceProduct(product.getCode());
        machine.addToBalance(-product.getPrice());
        double change = machine.getPaymentHandler().returnChange(machine.getCurrentBalance());

        if (change > 0.0) {
            System.out.println("Please collect your change: $" + change);
        }

        // Reset machine
        machine.resetTransaction();;
        machine.setState(new IdleState(machine));  // Go back to idle
    }

    @Override
    public void cancel() {
        throw new IllegalStateException("Cannot cancel during dispensing.");
    }
}
