package ATM_DESIGN;

import ATM_DESIGN.ChainofResponsibilt.DispenseChain;
import ATM_DESIGN.ChainofResponsibilt.FiftyDispense;
import ATM_DESIGN.ChainofResponsibilt.HundredDispenser;
import ATM_DESIGN.ChainofResponsibilt.TwentyDispenser;
import ATM_DESIGN.State.IdleState;

public class ATMDemo {
    public static void main(String[] args) {
        // Initialize ATM system (singleton will handle BankService and CoR setup internally)
        ATM atm = ATM.getInstance();

        // Use existing preloaded card and PIN from BankService
        Card card = new Card("1234-5678-9012-3456", "1234"); // Simulate inserting known card

        atm.insertCard(card);
        atm.enterPin("1234");

        atm.performTransaction(TransactionType.BALANCE_INQUIRY, 0);
        atm.performTransaction(TransactionType.CASH_WITHDRAWAL, 100);
        atm.performTransaction(TransactionType.CASH_DEPOSIT, 200);
        atm.performTransaction(TransactionType.CASH_WITHDRAWAL, 150);

        atm.ejectCard();
    }
}