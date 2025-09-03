package MOVIEBOOKING;

import MOVIEBOOKING.Enums.PaymentStatus;
import MOVIEBOOKING.Enums.SeatStatus;
import MOVIEBOOKING.PaymentStrategy.PaymentStrategy;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookingManager {
    private static volatile BookingManager instance;

    public BookingManager() {}

    public static BookingManager getInstance() {
        if (instance == null) {
            synchronized (BookingManager.class) {
                if (instance == null) {
                    instance = new BookingManager();
                }
            }
        }
        return instance;
    }

    public Optional<Booking> createBooking(User user, Show show, List<Seat> selectedSeats, PaymentStrategy paymentStrategy) {
        // Check if seats are available
        boolean allAvailable = selectedSeats.stream()
                .allMatch(seat -> seat.getStatus() == SeatStatus.AVAILABLE);

        if (!allAvailable) {
            System.err.println("Some selected seats are already booked or unavailable.");
            return Optional.empty();
        }

        // Calculate total price using show-specific pricing strategy
        double totalPrice = show.getPricingStrategy().calculatePrice(selectedSeats, show);

        // Attempt payment
        PaymentStatus paymentStatus = paymentStrategy.pay(totalPrice);
        if (paymentStatus != PaymentStatus.SUCCESS) {
            System.err.println("Payment failed.");
            return Optional.empty();
        }

        // Lock and mark seats as BOOKED
        selectedSeats.forEach(seat -> seat.setSeatStatus(SeatStatus.BOOKED));

        // Create booking using Builder pattern
        Booking booking = new Booking.Builder()
                .setBookingId(UUID.randomUUID().toString())
                .setUser(user)
                .setShow(show)
                .setBookedSeats(selectedSeats)
                .setTotalAmount(totalPrice)
                .build();

        // Add booking to user history
        user.addBooking(booking);

        return Optional.of(booking);
    }
}
