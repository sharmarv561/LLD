package HotelManagementSystem.Decorator;

public class RoomFeatureDecorator  implements RoomFeature{
    protected final RoomFeature decoratedRoom;

    public RoomFeatureDecorator(RoomFeature decoratedRoom) {
        this.decoratedRoom = decoratedRoom;
    }

    @Override
    public String getDescription() {
        return decoratedRoom.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedRoom.getCost();
    }

}
