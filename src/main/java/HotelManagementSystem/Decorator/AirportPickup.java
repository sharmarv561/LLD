package HotelManagementSystem.Decorator;

public class AirportPickup extends RoomFeatureDecorator {


    public AirportPickup(RoomFeature decoratedRoom) {
        super(decoratedRoom);
}

@Override
public String getDescription() {
    return super.getDescription() + ", Airport Pickup";
}

@Override
public double getCost() {
    return super.getCost() + 30.0; // $30 for pickup
}
}
