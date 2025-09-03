package HotelManagementSystem.Observer;

import HotelManagementSystem.Booking;

public class SMSNotifier implements BookingObserver{
    @Override
    public void update(Booking booking) {
        System.out.println("--- SMS Notification ---");
        System.out.println("To: [Guest's Phone Number]"); // Assuming guest has a phone number
        System.out.println("Message: Booking " + booking.getReservationId() + " for Room " + booking.getRoom().getRoomNumber() + " confirmed!");
        System.out.println("------------------------");
    }
}
