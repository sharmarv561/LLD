package MOVIEBOOKING;

import MOVIEBOOKING.Enums.SeatStatus;
import MOVIEBOOKING.Enums.SeatType;
import MOVIEBOOKING.PaymentStrategy.CreditCardPayment;
import MOVIEBOOKING.PaymentStrategy.PaymentStrategy;
import MOVIEBOOKING.PricingStrategy.PricingStrategyInterface;
import MOVIEBOOKING.PricingStrategy.WeekdayPricing;

import java.time.LocalDateTime;
import java.util.*;

public class MovieBookingDemo {

        public static void main(String[] args) {
            PricingStrategyInterface pricingStrategy = new WeekdayPricing();
            PaymentStrategy paymentStrategy = new CreditCardPayment("abc");


// Create movie
            Movie movie = new Movie("MOV1", "Inception", 148, "English");


// Create screen with seats
            Screen screen = new Screen("scr1", "IMAX",100);

            List<Seat> seats = new ArrayList<>();
            for (int i = 0; i < 3; i++) { // 3 rows
                for (int j = 0; j < 5; j++) { // 5 seats per row
                    String seatId = "S-" + i + "-" + j;
                    Seat seat = new Seat(seatId, i, j, SeatType.REGULAR);
                    seats.add(seat);
                    screen.addSeat(seat); // assuming addSeat() exists in Screen class
                }
            }


// Create show
            Show show = new Show("SHOW1", movie, screen, LocalDateTime.now().plusHours(2), pricingStrategy);


// Create user
            User user = new User("USR1", "Alice", "alice@example.com");


// Simulate seat selection
            List<Seat> selectedSeats = Arrays.asList(
                    screen.getAvailableSeats().get(0), // A1
                    screen.getAvailableSeats().get(2) // A3
            );


// Book ticket
            BookingManager bookingService = BookingManager.getInstance();
            Optional<Booking> bookingOpt = bookingService.createBooking(user, show, selectedSeats, paymentStrategy);


// Print result
            bookingOpt.ifPresentOrElse(
                    booking -> {
                        System.out.println("Booking Successful!");
                        System.out.println("Booking ID: " + booking.getBookingId());
                        System.out.println("Total Price: $" + booking.getTotalAmount());
                    },
                    () -> System.out.println("Booking Failed. Seats may be unavailable.")
            );
        }
}