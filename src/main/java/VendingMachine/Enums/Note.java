package VendingMachine.Enums;

public enum Note {
    ONE(1.0),
    FIVE(5.0),
    TEN(10.0);

    private final double value;

    Note(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
