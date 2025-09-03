package HotelManagementSystem.RStates;

import HotelManagementSystem.Room;

public class BookedState implements RoomState{

    @Override
    public void book(Room room) {
        System.out.println("Room is already booked.");
    }

    @Override
    public void checkIn(Room room) {
        System.out.println("Guest checked in.");
        room.setState(new OccupiedState());
    }

    @Override
    public void checkOut(Room room) {
        System.out.println("Cannot check out. Guest hasn't checked in yet.");
    }

    @Override
    public void markUnderMaintenance(Room room) {
        System.out.println("Cannot mark booked room as maintenance.");
    }
}
