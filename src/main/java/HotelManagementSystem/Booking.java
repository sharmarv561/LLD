package HotelManagementSystem;

import HotelManagementSystem.Enums.BookingStatus;
import HotelManagementSystem.Observer.BookingObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Booking {
    private final String reservationId;
    private final Guest guest;
    private final Room room;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private BookingStatus status;

    public Booking(Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.reservationId = UUID.randomUUID().toString();
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = BookingStatus.CONFIRMED;
        room.book(); //triggers state change
    }
    private final List<BookingObserver> observers = new ArrayList<>();

    // Observer methods
    public void addObserver(BookingObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (BookingObserver observer : observers) {
            observer.update(this);
        }
    }

    // Getters
    public String getReservationId() { return reservationId; }
    public Guest getGuest() { return guest; }
    public Room getRoom() { return room; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public BookingStatus getStatus() { return status; }

    // Reservation lifecycle methods
    public void checkIn() {
        if (status == BookingStatus.CONFIRMED) {
            room.checkIn(); // triggers state change
            this.status = BookingStatus.CHECKED_IN;
            notifyObservers(); // notify on check-in
            System.out.println("Guest checked in: " + guest.getName());
        } else {
            System.out.println("Cannot check in. Reservation is not in confirmed state.");
        }
    }

    public void checkOut() {
        if (status == BookingStatus.CHECKED_IN) {
            room.checkOut(); // triggers state change
            this.status = BookingStatus.CHECKED_OUT;
            notifyObservers(); // notify on check-out
            System.out.println("Guest checked out: " + guest.getName());
        } else {
            System.out.println("Cannot check out. Guest is not currently checked in.");
        }
    }

    public void cancel() {
        if (status == BookingStatus.CONFIRMED) {
            this.status = BookingStatus.CANCELLED;
            room.markUnderMaintenance(); // Or room.book() depending on logic
            notifyObservers(); // notify on check-out
            System.out.println("Reservation cancelled for guest: " + guest.getName());
        } else {
            System.out.println("Cannot cancel. Reservation is already " + status);
        }
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", guest=" + guest.getName() +
                ", room=" + room.getRoomNumber() +
                ", checkIn=" + checkInDate +
                ", checkOut=" + checkOutDate +
                ", status=" + status +
                '}';
    }
}