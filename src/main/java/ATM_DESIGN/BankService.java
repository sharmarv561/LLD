package ATM_DESIGN;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {

    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final Map<String, Card> cards = new ConcurrentHashMap<>();
    private final Map<Card, Account> cardAccountMap = new ConcurrentHashMap<>();

    public BankService() {
        // Initialize sample data for testing
        Account account1 = createAccount("1234567890", 1000.0);
        Card card1 = createCard("1234-5678-9012-3456", "1234");
        linkCardToAccount(card1, account1);

        Account account2 = createAccount("9876543210", 500.0);
        Card card2 = createCard("9876-5432-1098-7654", "4321");
        linkCardToAccount(card2, account2);
    }

    /**
     * Creates a new account with an account number and initial balance.
     */
    public Account createAccount(String accountNumber, double initialBalance) {
        Account account = new Account(accountNumber, initialBalance);
        accounts.put(accountNumber, account);
        return account;
    }

    /**
     * Creates a new card with a card number and PIN.
     */
    public Card createCard(String cardNumber, String pin) {
        Card card = new Card(cardNumber, pin);
        cards.put(cardNumber, card);
        return card;
    }

    /**
     * Authenticates the card using the provided PIN.
     */
    public boolean authenticate(Card card, String pin) {
        return card != null && card.validatePin(pin);
    }

    /**
     * Gets the card by card number.
     */
    public Card getCard(String cardNumber) {
        return cards.get(cardNumber);
    }

    /**
     * Returns the balance of the account linked to the card.
     */
    public double getBalance(Card card) {
        Account account = cardAccountMap.get(card);
        if (account == null) throw new IllegalArgumentException("Card not linked to any account.");
        return account.getBalance();
    }

    /**
     * Withdraws money from the account linked to the card.
     */
    public void withdrawMoney(Card card, double amount) {
        Account account = cardAccountMap.get(card);
        if (account == null) throw new IllegalArgumentException("Card not linked to any account.");
        account.debit(amount);
    }

    /**
     * Deposits money into the account linked to the card.
     */
    public void depositMoney(Card card, double amount) {
        Account account = cardAccountMap.get(card);
        if (account == null) throw new IllegalArgumentException("Card not linked to any account.");
        account.credit(amount);
    }

    /**
     * Links a card to a specific account and updates both mappings.
     */
    public void linkCardToAccount(Card card, Account account) {
        account.getAllCards().put(card.getCardNumber(), card);
        cardAccountMap.put(card, account);
    }
}
