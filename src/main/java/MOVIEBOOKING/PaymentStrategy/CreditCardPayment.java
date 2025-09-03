package MOVIEBOOKING.PaymentStrategy;

import MOVIEBOOKING.Enums.PaymentStatus;

public class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public PaymentStatus pay(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
        return PaymentStatus.SUCCESS;
    }
}
