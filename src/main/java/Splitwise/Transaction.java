package Splitwise;

public class Transaction {
    private final User from;
    private final User to;
    private final double amount;
    private TransactionStatus status;

    public Transaction(User from, User to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.status = TransactionStatus.PENDING;
    }

    public void execute() {
        if (status == TransactionStatus.COMPLETED) {
            throw new IllegalStateException("Transaction already completed.");
        }
        from.subtractBalance(to, amount);
        to.addBalance(from, amount);
        status = TransactionStatus.COMPLETED;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return from.getName() + " should pay " + to.getName() + " $" + String.format("%.2f", amount);
    }
}

