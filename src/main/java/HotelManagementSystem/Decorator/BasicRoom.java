package HotelManagementSystem.Decorator;

import HotelManagementSystem.Room;

public class BasicRoom implements RoomFeature{

    private final Room room;

    public BasicRoom(Room room) {
        this.room = room;
    }

    @Override
    public String getDescription() {
        return "Room " + room.getRoomNumber() + " (" + room.getRoomType() + ", " + room.getRoomStyle() + ")";
    }

    @Override
    public double getCost() {
        return room.getPrice();
    }
}
