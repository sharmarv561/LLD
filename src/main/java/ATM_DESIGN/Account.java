package ATM_DESIGN;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Account {
    private final String accountNumber;
    private double balance;
    // Map of card number to Card
    private final Map<String, Card> cards;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.cards = new ConcurrentHashMap<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized boolean debit(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

    public synchronized void credit(double amount) {
        balance += amount;
    }



    public Map<String, Card> getAllCards() {
        return new ConcurrentHashMap<>(cards);
    }
}
