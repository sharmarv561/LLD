package VendingMachine;

import VendingMachine.Enums.Coin;
import VendingMachine.Enums.Note;
import VendingMachine.States.IdleState;
import VendingMachine.States.VendingMachineState;

public class VendingMachineSystem {

    private static VendingMachineSystem instance;

    private VendingMachineState currentState;
    private final Inventory inventory;
    private final PaymentHandler paymentHandler;

    private Product selectedProduct;
    private double currentBalance;

    private VendingMachineSystem() {
        this.inventory = new Inventory();
        this.paymentHandler = new PaymentHandler();
        this.currentState = new IdleState(this);
        this.currentBalance = 0.0;
    }

    public static synchronized VendingMachineSystem getInstance() {
        if (instance == null) {
            instance = new VendingMachineSystem();
        }
        return instance;
    }

    // State transitions
    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    // Delegation methods
    public void selectProduct(String productCode) {
        currentState.selectProduct(productCode);
    }

    public void insertCoin(Coin coin) {
        currentState.insertCoin(coin);
    }

    public void insertNote(Note note) {
        currentState.insertNote(note);
    }

    public void dispense() {
        currentState.dispense();
    }

    public void returnChange() {
        currentState.returnChange();
    }

    public void cancel() {
        currentState.cancel();
    }

    public PaymentHandler getPaymentHandler() {
        return paymentHandler;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void addToBalance(double amount) {
        this.currentBalance += amount;
    }

    public void resetTransaction() {
        this.selectedProduct = null;
        this.currentBalance = 0.0;
    }

    public Inventory getInventory() {
        return inventory;
    }



}
