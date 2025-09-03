package HotelManagementSystem.RStates;

import HotelManagementSystem.Room;

public class MaintenanceState implements RoomState {
    @Override
    public void book(Room room) {
        System.out.println("Cannot book. Room is under maintenance.");
    }

    @Override
    public void checkIn(Room room) {
        System.out.println("Cannot check in. Room is under maintenance.");
    }

    @Override
    public void checkOut(Room room) {
        System.out.println("Cannot check out. Room is under maintenance.");
    }

    @Override
    public void markUnderMaintenance(Room room) {
        System.out.println("Room is already under maintenance.");
    }
}