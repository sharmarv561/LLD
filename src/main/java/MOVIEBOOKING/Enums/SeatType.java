package MOVIEBOOKING.Enums;

public enum SeatType {
    REGULAR(10.0),
    PREMIUM(15.0),
    VIP(20.0);

    private final double basePrice;

    SeatType(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }
}
