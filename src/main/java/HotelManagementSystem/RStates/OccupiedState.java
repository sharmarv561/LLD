package HotelManagementSystem.RStates;

import HotelManagementSystem.Room;

public class OccupiedState implements RoomState{

    @Override
    public void book(Room room) {
        System.out.println("Cannot book. Room is currently occupied.");
    }

    @Override
    public void checkIn(Room room) {
        System.out.println("Already checked in.");
    }

    @Override
    public void checkOut(Room room) {
        System.out.println("Checked out successfully.");
        room.setState(new AvailableState());
    }

    @Override
    public void markUnderMaintenance(Room room) {
        System.out.println("Cannot mark occupied room as maintenance.");
    }
}
