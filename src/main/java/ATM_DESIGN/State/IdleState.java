package ATM_DESIGN.State;

import ATM_DESIGN.ATM;
import ATM_DESIGN.Card;
import ATM_DESIGN.TransactionType;

public class IdleState implements ATMState{


    @Override
    public void insertCard(ATM atm, Card inputCard) {
        System.out.println("\nCard has been inserted.");
        Card card = atm.getBankService().getCard(inputCard.getCardNumber());

        if (card == null) {
            ejectCard(atm);
        } else {
            atm.setCurrentCard(card);
            atm.changeState(new HasCardState());
        }
    }

    @Override
    public void enterPin(ATM atm, String pin) {
        System.out.println("Error: Please insert a card first.");
    }

    @Override
    public void performTransaction(ATM atm, TransactionType type, double amount) {
        System.out.println("Error: Please insert a card first.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("Error: Card not found.");
        atm.setCurrentCard(null);
    }
}
