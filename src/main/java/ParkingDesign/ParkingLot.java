package ParkingDesign;

import ParkingDesign.ParkingStrategies.PrakingStrategy;
import ParkingDesign.feeStrategies.FeeStrategy;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {
    private static ParkingLot instance;

    private final List<ParkingFloor> floors;
    private final Map<String, Ticket> activeTickets;

    private FeeStrategy feeStrategy;
    private PrakingStrategy prakingStrategy;

    public ParkingLot(){

        this.floors = new ArrayList<>();
        this.activeTickets = new HashMap<>();
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void setParkingStrategy(PrakingStrategy parkingStrategy) {
        this.prakingStrategy = parkingStrategy;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        if (prakingStrategy == null) {
            throw new IllegalStateException("Parking strategy not set");
        }

        ParkingSpot spot = prakingStrategy.findSpot(floors, vehicle.getType());

        if (spot == null || !spot.assignVehicle(vehicle)) {
            System.out.println("No available spot for vehicle");
            return null;
        }

        // Create ticket
        Ticket ticket = new Ticket(UUID.randomUUID().toString(), vehicle, LocalDateTime.now(), spot);
        activeTickets.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    public double unparkVehicle(String ticketId) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            System.out.println("Invalid ticket");
            return -1;
        }

        ParkingSpot spot = ticket.getSpot();
        spot.removeVehicle();

        LocalDateTime exitTime = LocalDateTime.now();
        double fee = feeStrategy.calulateFee(ticket, exitTime);

        activeTickets.remove(ticketId);
        return fee;
    }

    public int getAvailableSpots(VehicleType type) {
        int count = 0;
        for (ParkingFloor floor : floors) {
            count += floor.getAvailableSpotCount(type);
        }
        return count;
    }
}
