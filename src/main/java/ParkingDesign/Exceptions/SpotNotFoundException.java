package ParkingDesign.Exceptions;

public class SpotNotFoundException extends RuntimeException {
    public SpotNotFoundException(String message) {
        super(message);
    }
}