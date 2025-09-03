package MOVIEBOOKING;

import MOVIEBOOKING.Enums.PaymentStatus;
import MOVIEBOOKING.PaymentStrategy.PaymentStrategy;

import java.time.LocalDateTime;

public class Payment {
    private final double amount;
    private final PaymentStrategy strategy;
    private final PaymentStatus status;
    private final LocalDateTime timestamp;

    public Payment(double amount, PaymentStrategy strategy) {
        this.amount = amount;
        this.strategy = strategy;
        this.status = strategy.pay(amount);
        this.timestamp = LocalDateTime.now();
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public PaymentStrategy getStrategy() {
        return strategy;
    }
}
