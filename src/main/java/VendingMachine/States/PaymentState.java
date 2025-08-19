package VendingMachine.States;

import VendingMachine.Enums.Coin;
import VendingMachine.Enums.Note;
import VendingMachine.Product;
import VendingMachine.VendingMachineSystem;

public class PaymentState implements VendingMachineState{

    private final VendingMachineSystem machine;

    public PaymentState(VendingMachineSystem machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(String code) {
        System.out.println("Product already selected.");
    }

    @Override
    public void insertCoin(Coin coin) {
//        machine.getPaymentHandler().insertCoins(coin);
//        machine.addToBalance(coin.getValue());
//        System.out.println("Inserted coin: $" + coin.getValue() + ". Total: $" + machine.getCurrentBalance());
//
//        if (machine.getCurrentBalance() >= machine.getSelectedProduct().getPrice()) {
//            System.out.println("Sufficient payment received.");
//    }
        System.out.println("Already received full amount.");

    }

    @Override
    public void insertNote(Note note) {
//        machine.getPaymentHandler().insertNoted(note);
//        machine.addToBalance(note.getValue());
//        checkPaymentStatus();
        System.out.println("Already received full amount.");
    }

    private void checkPaymentStatus() {
        Product product = machine.getSelectedProduct();
        double paid = machine.getCurrentBalance();

        if (paid >= product.getPrice()) {
            System.out.println("Payment complete. Preparing to dispense.");
            machine.setState(new DispenseState(machine));
        } else {
            System.out.println("Please insert more money. Remaining: $" + (product.getPrice() - paid));
        }
    }

    @Override
    public void returnChange() {
        double change = machine.getPaymentHandler().returnChange(machine.getCurrentBalance());
        machine.resetTransaction();
        System.out.println("Returning change: $" + change);
        machine.setState(new IdleState(machine));
    }

    @Override
    public void dispense() {
        if (machine.getCurrentBalance() >= machine.getSelectedProduct().getPrice()) {
            machine.setState(new DispenseState(machine));
            machine.dispense();
        } else {
            System.out.println("Insufficient balance. Please add more money.");
        }
    }

    @Override
    public void cancel() {
        double change = machine.getPaymentHandler().returnChange(machine.getCurrentBalance());
        if (change > 0.0) {
            System.out.println("Returning change: $" + change);
        } else {
            System.out.println("No money to return.");
        }
        machine.resetTransaction();
        machine.setState(new IdleState(machine));
    }
}
