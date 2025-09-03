package HotelManagementSystem.RStates;

import HotelManagementSystem.Room;

public class AvailableState implements RoomState{
    @Override
    public void book(Room room) {
        System.out.println("Room booked successfully.");
        room.setState(new BookedState());
    }

    @Override
    public void checkIn(Room room) {
        System.out.println("Cannot check in. Room must be booked first.");
    }

    @Override
    public void checkOut(Room room) {
        System.out.println("Cannot check out. Room is not occupied.");
    }

    @Override
    public void markUnderMaintenance(Room room) {
        System.out.println("Room is now under maintenance.");
        room.setState(new MaintenanceState());
    }
}
