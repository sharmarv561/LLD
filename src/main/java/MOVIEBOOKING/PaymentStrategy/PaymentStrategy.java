package MOVIEBOOKING.PaymentStrategy;

import MOVIEBOOKING.Enums.PaymentStatus;

public interface PaymentStrategy {
    PaymentStatus pay(double amount);
}
