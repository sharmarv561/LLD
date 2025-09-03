package HotelManagementSystem;

import HotelManagementSystem.Enums.*;
import HotelManagementSystem.Decorator.*;
import HotelManagementSystem.Factory.RoomFactory;
import HotelManagementSystem.Observer.*;
import HotelManagementSystem.Strategy.*;

import java.time.LocalDate;
import java.util.*;

public class HotelManagementSystem {
    private final Map<String, Guest> guests = new HashMap<>();
    private final Map<String, Room> rooms = new HashMap<>();
    private final Map<String, Booking> bookings = new HashMap<>();
    private final Map<String, RoomFeature> decoratedRooms = new HashMap<>();

    // Guest management
    public Guest registerGuest(String name, String email, String phone) {
        Guest guest = new Guest(name, email, phone);
        guests.put(name, guest);
        return guest;
    }

    public Guest getGuest(String name) {
        return guests.get(name);
    }

    // Room management
    public Room addRoom(String roomNumber, RoomType type, RoomStyle style) {
        Room room = RoomFactory.createRoom(roomNumber, type, style);
        rooms.put(roomNumber, room);
        return room;
    }

    public Room getRoom(String roomNumber) {
        return rooms.get(roomNumber);
    }

    // Booking
    public Booking bookRoom(String guestName, String roomNumber, LocalDate checkIn, LocalDate checkOut) {
        Guest guest = getGuest(guestName);
        Room room = getRoom(roomNumber);
        Booking booking = new Booking(guest, room, checkIn, checkOut);
        booking.addObserver(new SMSNotifier());
        bookings.put(guestName, booking);
        decoratedRooms.put(guestName, new BasicRoom(room));
        return booking;
    }

    public void addRoomFeature(String guestName, RoomFeature feature) {
        RoomFeature current = decoratedRooms.get(guestName);
        RoomFeature updated = null;
        if (feature instanceof Breakfast)
            updated = new Breakfast(current);
        else if (feature instanceof AirportPickup)
            updated = new AirportPickup(current);

        decoratedRooms.put(guestName, updated);
    }

    public void checkIn(String guestName) {
        bookings.get(guestName).checkIn();
    }

    public void checkOut(String guestName) {
        bookings.get(guestName).checkOut();
    }

    public void pay(String guestName, PaymentStrategy strategy) {
        RoomFeature decoratedRoom = decoratedRooms.get(guestName);
        strategy.pay(decoratedRoom.getCost());
    }
}
