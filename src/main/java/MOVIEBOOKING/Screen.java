package MOVIEBOOKING;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final String screenId;
    private final String name;
    private final int capacity;
    private final List<Seat> seats;

    public Screen(String screenId, String name, int capacity) {
        this.screenId = screenId;
        this.name = name;
        this.capacity = capacity;
        this.seats = new ArrayList<>();
    }

    public void addSeat(Seat seat) {
        if (seats.size() < capacity) {
            seats.add(seat);
        } else {
            throw new IllegalStateException("Screen capacity exceeded.");
        }
    }

    public List<Seat> getAllSeats() {
        return seats;
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

    public int getCapacity() {
        return capacity;
    }

    public String getScreenId() {
        return screenId;
    }

    public String getName() {
        return name;
    }
}
