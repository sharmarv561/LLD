package HotelManagementSystem;

import HotelManagementSystem.Enums.RoomStyle;
import HotelManagementSystem.Enums.RoomType;
import HotelManagementSystem.RStates.AvailableState;
import HotelManagementSystem.RStates.RoomState;

public class Room {
    private String roomNumber;
    private RoomType roomType;
    private RoomStyle roomStyle;
    private double price;

    private RoomState currentState;

    public Room(String roomNumber, RoomType roomType, RoomStyle roomStyle, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomStyle = roomStyle;
        this.price = price;
        this.currentState = new AvailableState(); // initial state
    }

    public void setState(RoomState state) {
        this.currentState = state;
    }

    public RoomState getState() {
        return currentState;
    }

    public void book() {
        currentState.book(this);
    }

    public void checkIn() {
        currentState.checkIn(this);
    }

    public void checkOut() {
        currentState.checkOut(this);
    }

    public void markUnderMaintenance() {
        currentState.markUnderMaintenance(this);
    }

    public String getRoomNumber() {
       return roomNumber;
    }

    public RoomStyle getRoomStyle() {
        return roomStyle;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    // Getters and setters for other fields
}
