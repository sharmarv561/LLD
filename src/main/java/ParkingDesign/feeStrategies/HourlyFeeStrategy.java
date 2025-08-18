package ParkingDesign.feeStrategies;

import ParkingDesign.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;

public class HourlyFeeStrategy implements FeeStrategy{
    private final double hourlyRate;

    public HourlyFeeStrategy(double hourlyRate){
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calulateFee(Ticket ticket, LocalDateTime exitTime){
        Duration duration = Duration.between(ticket.getEntryTime(), exitTime);
        long hours = duration.toHours();
        if(duration.toMinutes() %60 != 0){
            hours++;     //Round up to next full hour
        }
        return hours*hourlyRate;
    }
}
/*
Why interface?

“To allow flexible and extensible fee structures. I can easily add VehicleBasedStrategy, WeekendStrategy, FixedFlatRate, etc.
without changing existing code — following the Open/Closed Principle.”

Hourly Rounding Up:

“We round up partial hours for simplicity — this is common in real-world parking lots to ensure customers are charged for every hour entered.”

Constructor Injection:

“The hourly rate is passed via constructor, allowing different rates for different contexts (e.g., premium vs regular floors).”
 */