package VendingMachine.Enums;

public enum Coin {
    PENNY(0.01),
    NICKEL(0.05),
    DIME(0.10),
    QUARTER(0.25);

    private final double value;

    Coin(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
/*
I’ve separated coins and notes into distinct enums because in a real-world system, they’re usually processed differently by the machine —
 for instance, notes are accepted through a bill acceptor while coins might be stored separately and used for giving change. \
This separation supports better SRP (Single Responsibility Principle) and extensibility if we later introduce more advanced logic like different limits, fraud checks, or replenishment
 */