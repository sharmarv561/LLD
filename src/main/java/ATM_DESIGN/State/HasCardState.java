package ATM_DESIGN.State;

import ATM_DESIGN.ATM;
import ATM_DESIGN.Card;
import ATM_DESIGN.TransactionType;

public class HasCardState implements ATMState {

    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("Error: A card is already inserted.");
    }

    @Override
    public void enterPin(ATM atm, String pin) {
        Card currentCard = atm.getCurrentCard();
        if (currentCard == null) {
            System.out.println("Error: No card inserted.");
            atm.changeState(new IdleState());
            return;
        }

        if (currentCard.validatePin(pin)) {
            System.out.println("PIN validated successfully.");
            atm.setAuthenticated(true);
            atm.changeState(new AuthenticatedCard());
        } else {
            System.out.println("Invalid PIN. Please try again.");
        }
    }

    @Override
    public void performTransaction(ATM atm, TransactionType type, double amount) {
        System.out.println("Error: Please enter PIN first.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("Card ejected.");
        atm.setCurrentCard(null);
        atm.setAuthenticated(false);
        atm.changeState(new IdleState());
    }
}