package HotelManagementSystem.Observer;

import HotelManagementSystem.Booking;

public class EmailNotifier implements BookingObserver{
    @Override
    public void update(Booking booking) {
        System.out.println("--- Email Notification ---");
        System.out.println("To: " + booking.getGuest().getEmail());
        System.out.println("Subject: Booking Confirmation " + booking.getReservationId());
        System.out.println("Dear " + booking.getGuest().getName() + ",");
        System.out.println("Your booking for Room " + booking.getRoom().getRoomNumber() + " is confirmed.");
        System.out.println("--------------------------");
    }
}

