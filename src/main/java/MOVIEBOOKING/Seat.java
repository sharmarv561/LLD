package MOVIEBOOKING;

import MOVIEBOOKING.Enums.SeatStatus;
import MOVIEBOOKING.Enums.SeatType;

import java.util.Objects;

public class Seat {
    private final String seatId;
    private final int row;
    private final int number;
    private final SeatType type;
    private SeatStatus status;

    public Seat(String seatId, int row, int number, SeatType type) {
        this.seatId = seatId;
        this.row = row;
        this.number = number;
        this.type = type;
        this.status = SeatStatus.AVAILABLE;
    }

    public void lock() {
        if (status == SeatStatus.AVAILABLE) {
            status = SeatStatus.LOCKED;
        }
    }

    public void book() {
        if (status == SeatStatus.LOCKED || status == SeatStatus.AVAILABLE) {
            status = SeatStatus.BOOKED;
        }
    }

    public void release() {
        status = SeatStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return status == SeatStatus.AVAILABLE;
    }

    // Getters
    public String getSeatId() { return seatId; }
    public int getRow() { return row; }
    public int getNumber() { return number; }
    public SeatType getSeatType() { return type; }
    public SeatStatus getStatus() { return status; }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.status = seatStatus;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seat)) return false;
        Seat seat = (Seat) o;
        return Objects.equals(seatId, seat.seatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatId);
    }

}

