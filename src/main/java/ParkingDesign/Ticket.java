package ParkingDesign;

import java.time.LocalDateTime;

public class Ticket {

    private final String ticketId;
    private final Vehicle vehicle;
    private final LocalDateTime entryTime;
    private final ParkingSpot spot;

    public Ticket(String ticketId, Vehicle vehicle, LocalDateTime entryTime, ParkingSpot spot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.spot = spot;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public ParkingSpot getSpot() {
        return spot;
    }
}
/*
I marked these fields as final because they represent fixed, unchanging properties of a parking session.
This ensures immutability and improves both safety and clarity, especially in a concurrent system.
It also helps communicate intent to other developers.
 */
