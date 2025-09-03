package HotelManagementSystem.RStates;

import HotelManagementSystem.Room;

public interface RoomState {

    void book(Room room);
    void checkIn(Room room);
    void checkOut(Room room);
    void markUnderMaintenance(Room room);

}
