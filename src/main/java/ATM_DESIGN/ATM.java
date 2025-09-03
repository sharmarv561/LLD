package ATM_DESIGN;

import ATM_DESIGN.ChainofResponsibilt.DispenseChain;
import ATM_DESIGN.ChainofResponsibilt.HundredDispenser;
import ATM_DESIGN.State.ATMState;
import ATM_DESIGN.State.IdleState;

public class ATM {
    private static volatile ATM instance;

    private ATMState currentState;
    private final BankService bankService;
    private final DispenseChain dispenseChain;
    private Card currentCard;
    private boolean isAuthenticated;

    private ATM() {
        this.bankService = new BankService();

        // 🟡 Set up Chain of Responsibility for note dispensing
        DispenseChain c1 = new HundredDispenser(10); // 10 x $100 notes
        DispenseChain c2 = new HundredDispenser(10); // 10 x $100 notes
        DispenseChain c3 = new HundredDispenser(10); // 10 x $100 notes
        c1.setNextDispenser(c2);
        c2.setNextDispenser(c3);

        this.dispenseChain = c1; // root of the chain
        this.currentState = new IdleState();        // Start in Idle state
    }

    public static ATM getInstance() {
        if (instance == null) {
            instance = new ATM();
        }
        return instance;
    }

    // Core ATM operations delegated to the current state
    public void insertCard(Card card) {
        currentState.insertCard(this, card);
    }

    public void enterPin(String pin) {
        currentState.enterPin(this, pin);
    }

    public void performTransaction(TransactionType type, double amount) {
        currentState.performTransaction(this, type, amount);
    }

    public void ejectCard() {
        currentState.ejectCard(this);
    }

    // State management
    public void changeState(ATMState newState) {
        this.currentState = newState;
    }

    // Getters and setters
    public ATMState getCurrentState() {
        return currentState;
    }

    public BankService getBankService() {
        return bankService;
    }

    public DispenseChain getCashDispenser() {
        return dispenseChain;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }
}
