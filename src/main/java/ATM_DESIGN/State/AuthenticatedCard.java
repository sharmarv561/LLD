package ATM_DESIGN.State;

import ATM_DESIGN.ATM;
import ATM_DESIGN.Card;
import ATM_DESIGN.TransactionType;

public class AuthenticatedCard implements ATMState {

    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("Error: A card is already inserted.");
    }

    @Override
    public void enterPin(ATM atm, String pin) {
        System.out.println("Error: PIN already entered.");
    }

    @Override
    public void performTransaction(ATM atm, TransactionType type, double amount) {
        switch (type) {
            case BALANCE_INQUIRY -> {
                double balance = atm.getBankService().getBalance(atm.getCurrentCard());
                System.out.println("Current Balance: $" + balance);
            }

            case CASH_WITHDRAWAL -> {
                if (!atm.getCashDispenser().canDispense((int) amount)) {
                    System.out.println("ATM has insufficient funds or cannot dispense requested denomination.");
                    return;
                }

                double currentBalance = atm.getBankService().getBalance(atm.getCurrentCard());
                if (currentBalance < amount) {
                    System.out.println("Insufficient account balance.");
                    return;
                }

                atm.getBankService().withdrawMoney(atm.getCurrentCard(), amount);
                atm.getCashDispenser().dispense((int) amount);
                System.out.println("Please collect your cash. $" + amount + " withdrawn.");
            }

            case CASH_DEPOSIT -> {
                atm.getBankService().depositMoney(atm.getCurrentCard(), amount);
                System.out.println("Deposit successful. $" + amount + " added.");
            }

            default -> System.out.println("Invalid transaction type.");
        }
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("Card ejected.");
        atm.setCurrentCard(null);
        atm.setAuthenticated(false);
        atm.changeState(new IdleState());
    }

}
