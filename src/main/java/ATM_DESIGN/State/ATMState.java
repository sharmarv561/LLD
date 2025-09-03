package ATM_DESIGN.State;

import ATM_DESIGN.ATM;
import ATM_DESIGN.Card;
import ATM_DESIGN.TransactionType;

public interface ATMState {
    void insertCard(ATM atm, Card cardNumber);
    void enterPin(ATM atm, String pin);
    void performTransaction(ATM atm, TransactionType type, double amount);
    void ejectCard(ATM atm);

}
