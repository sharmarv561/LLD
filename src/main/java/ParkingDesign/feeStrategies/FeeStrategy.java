package ParkingDesign.feeStrategies;

import ParkingDesign.Ticket;

import java.time.LocalDateTime;

public interface FeeStrategy {
    double calulateFee(Ticket ticket, LocalDateTime exitTime);
}
