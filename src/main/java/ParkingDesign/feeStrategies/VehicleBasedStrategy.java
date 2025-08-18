package ParkingDesign.feeStrategies;

import ParkingDesign.Ticket;
import ParkingDesign.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class VehicleBasedStrategy implements FeeStrategy{
//    private final Map<VehicleType, Double> hourlyRates;

    @Override
    public double calulateFee(Ticket ticket, LocalDateTime exitTime) {
        LocalDateTime entryTime = ticket.getEntryTime();
        long hours = ChronoUnit.HOURS.between(entryTime, exitTime);
        if (hours == 0) hours = 1; // Minimum charge for 1 hour

        VehicleType type = ticket.getVehicle().getType();

        switch (type) {
            case CAR:
                return hours * 10; // $10 per hour
            case BIKE:
                return hours * 5; // $5 per hour
            case TRUCK:
                return hours * 15; // $15 per hour
            default:
                throw new IllegalArgumentException("Unknown vehicle type");
        }
    }
}

//“This strategy allows custom rates per vehicle type, making it extensible.
// You can easily support promotions or premium vehicle pricing with minimal code changes.”