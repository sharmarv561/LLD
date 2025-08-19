package VendingMachine.States;

import VendingMachine.Enums.Coin;
import VendingMachine.Enums.Note;
import VendingMachine.VendingMachineSystem;

public class ReadyState implements VendingMachineState{

    private final VendingMachineSystem machine;

    public ReadyState(VendingMachineSystem machine) {
        this.machine = machine;
    }
    @Override
    public void selectProduct(String code) {
        System.out.println("Product already selected. Cancel to choose a different product.");
    }

    @Override
    public void insertCoin(Coin coin) {
        double value = coin.getValue();
        machine.getPaymentHandler().insertCoins(coin);
        machine.addToBalance(value);

        System.out.println("Inserted coin: $" + value + ". Total: $" + machine.getCurrentBalance());

        if (machine.getCurrentBalance() >= machine.getSelectedProduct().getPrice()) {
            System.out.println("Sufficient payment received.");
            machine.setState(new PaymentState(machine));
        }
    }

    @Override
    public void insertNote(Note note) {
        double value = note.getValue();
        machine.getPaymentHandler().insertNoted(note);
        machine.addToBalance(value);

        System.out.println("Inserted note: $" + value + ". Total paid: $" + machine.getCurrentBalance());

        if (machine.getCurrentBalance() >= machine.getSelectedProduct().getPrice()) {
            System.out.println("Sufficient payment received.");
            machine.setState(new PaymentState(machine));
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
        System.out.println("Insufficient payment. Please insert more coins or notes.");
    }

    @Override
    public void cancel() {
        double change = machine.getPaymentHandler().returnChange(machine.getCurrentBalance());
        System.out.println("Transaction cancelled. Refunding: $" + change);
        machine.resetTransaction();
        machine.setState(new IdleState(machine));
    }
}
