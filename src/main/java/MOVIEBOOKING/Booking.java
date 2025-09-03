package MOVIEBOOKING;

import MOVIEBOOKING.Enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Booking {
    private final String bookingId;
    private final User user;
    private final Show show;
    private final List<Seat> bookedSeats;
    private final double totalAmount;
    private final LocalDateTime bookingTime;
    private final BookingStatus status;
    private final Payment payment;

    private Booking(Builder builder) {
        this.bookingId = builder.bookingId;
        this.user = builder.user;
        this.show = builder.show;
        this.bookedSeats = builder.bookedSeats;
        this.totalAmount = builder.totalAmount;
        this.bookingTime = builder.bookingTime;
        this.status = builder.status;
        this.payment = builder.payment;
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public Payment getPayment() {
        return payment;
    }

    // Builder Class
    public static class Builder {
        private String bookingId;
        private User user;
        private Show show;
        private List<Seat> bookedSeats;
        private double totalAmount;
        private LocalDateTime bookingTime;
        private BookingStatus status;
        private Payment payment;

        public Builder setBookingId(String bookingId) {
            this.bookingId = bookingId;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setShow(Show show) {
            this.show = show;
            return this;
        }

        public Builder setBookedSeats(List<Seat> seats) {
            this.bookedSeats = seats;
            return this;
        }

        public Builder setTotalAmount(double amount) {
            this.totalAmount = amount;
            return this;
        }

        public Builder setBookingTime(LocalDateTime time) {
            this.bookingTime = time;
            return this;
        }

        public Builder setStatus(BookingStatus status) {
            this.status = status;
            return this;
        }

        public Booking build() {
            // You could add validation here if needed
            return new Booking(this);
        }

        public Builder setPayment(Payment payment) {
            this.payment = payment;
            return this;
        }


        public Builder setId(String id) {
            this.bookingId = id;
            return this;
        }
    }
}
