package ParkingDesign.feeStrategies;

import ParkingDesign.Ticket;

import java.time.LocalDateTime;

public class FlatRateStrategy implements FeeStrategy{

    private final double flatRate;

    public FlatRateStrategy(double flatRate) {
        this.flatRate = flatRate;
    }

    @Override
    public double calulateFee(Ticket ticket, LocalDateTime exitTime) {
        return flatRate;
    }
}
