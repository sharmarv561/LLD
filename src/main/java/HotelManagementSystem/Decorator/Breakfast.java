package HotelManagementSystem.Decorator;

public class Breakfast extends RoomFeatureDecorator {
    public Breakfast(RoomFeature decoratedRoom) {
        super(decoratedRoom);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Breakfast";
    }

    @Override
    public double getCost() {
        return super.getCost() + 20.0; // $20 for breakfast
    }
}
