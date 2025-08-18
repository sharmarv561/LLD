package Splitwise;

public class Split {

    private final User user;    // User who owes or is responsible for this split
    private final double amount; // Amount owed by the user in this expense

    public Split(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    // Getters
    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return user.getName() + " owes $" + String.format("%.2f", amount);
    }
}
