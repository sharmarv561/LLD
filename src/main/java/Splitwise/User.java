package Splitwise;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String email;
    private final Map<User,Double> balances;

    public User(String name, String email) {
        this.id = UUID.randomUUID().toString();;
        this.name = name;
        this.email = email;
        this.balances = new HashMap<>();
    }

    // Getters and setters for name/email
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addBalance(User other, Double amount){
        if (this.equals(other)) return; // Ignore self balances
        double currentBalance = balances.getOrDefault(other, 0.0);
        balances.put(other,currentBalance+amount);
    }

    public void subtractBalance(User other, double amount) {
        if (this.equals(other)) return; // Ignore self balances
        double currentBalance = balances.getOrDefault(other, 0.0);
        balances.put(other, currentBalance - amount);
    }

    // Get balance with another user (0 if none)
    public double getBalance(User other) {
        return balances.getOrDefault(other, 0.0);
    }

    public Map<User, Double> getBalances() {
        return balances;
    }

    public void showBalances() {
        System.out.println("--- Balance Sheet for " + this.getName() + " ---");
        if (balances.isEmpty()) {
            System.out.println("All settled up!");
            return;
        }

        double totalOwedToMe = 0;
        double totalIOwe = 0;

        for (Map.Entry<User, Double> entry : balances.entrySet()) {
            User otherUser = entry.getKey();
            if (this.equals(otherUser)) continue;
            double amount = entry.getValue();

            if (amount > 0.01) {
                System.out.println(otherUser.getName() + " owes " + this.getName() + " $" + String.format("%.2f", amount));
                totalOwedToMe += amount;
            } else if (amount < -0.01) {
                System.out.println(this.getName() + " owes " + otherUser.getName() + " $" + String.format("%.2f", -amount));
                totalIOwe += (-amount);
            }
        }
        System.out.println("Total Owed to " + this.getName() + ": $" + String.format("%.2f", totalOwedToMe));
        System.out.println("Total " + this.getName() + " Owes: $" + String.format("%.2f", totalIOwe));
        System.out.println("---------------------------------");
    }

}
