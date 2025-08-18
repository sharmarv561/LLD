package ParkingDesign;

public class SpotUnAvailableException extends RuntimeException {
    public SpotUnAvailableException(String message) {
        super(message);
    }
}