package MOVIEBOOKING;

import MOVIEBOOKING.Enums.SeatStatus;
import MOVIEBOOKING.PricingStrategy.PricingStrategyInterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Show {
    private final String showId;
    private final Movie movie;
    private final Screen screen;
    private final LocalDateTime showTime;
    private final List<Seat> seats;
    private final PricingStrategyInterface pricingStrategy;

    public Show(String showId, Movie movie, Screen screen, LocalDateTime showTime, PricingStrategyInterface pricingStrategy) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.showTime = showTime;
        this.pricingStrategy = pricingStrategy;

        // Deep copy of seats from screen to show
        this.seats = new ArrayList<>();
        for (Seat seat : screen.getAllSeats()) {
            Seat newSeat = new Seat(
                    seat.getSeatId(),
                    seat.getRow(),
                    seat.getNumber(),
                    seat.getSeatType()
            );
            this.seats.add(newSeat);
        }

        // Register this show with the movie
        this.movie.addShow(this);
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> available = new ArrayList<>();
        for (Seat seat : seats) {
            if (seat.isAvailable()) {
                available.add(seat);
            }
        }
        return available;
    }

    public boolean hasAvailableSeats() {
        for (Seat seat : seats) {
            if (seat.isAvailable()) return true;
        }
        return false;
    }

    public boolean lockSeat(String seatId) {
        for (Seat seat : seats) {
            if (seat.getSeatId().equals(seatId) && seat.isAvailable()) {
                seat.lock();
                return true;
            }
        }
        return false;
    }

    public boolean bookSeat(String seatId) {
        for (Seat seat : seats) {
            if (seat.getSeatId().equals(seatId) &&
                    (seat.getStatus() == SeatStatus.LOCKED || seat.getStatus() == SeatStatus.AVAILABLE)) {
                seat.book();
                return true;
            }
        }
        return false;
    }

    public void releaseSeat(String seatId) {
        for (Seat seat : seats) {
            if (seat.getSeatId().equals(seatId)) {
                seat.release();
                break;
            }
        }
    }

    public String getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public PricingStrategyInterface getPricingStrategy() {
        return pricingStrategy;
    }
}