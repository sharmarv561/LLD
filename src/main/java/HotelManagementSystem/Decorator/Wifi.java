package HotelManagementSystem.Decorator;

public class Wifi extends RoomFeatureDecorator{

    public  Wifi(RoomFeature decoratedRoom) {
        super(decoratedRoom);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Wi-Fi";
    }

    @Override
    public double getCost() {
        return super.getCost() + 10.0; // $10 for WiFi
    }
}
