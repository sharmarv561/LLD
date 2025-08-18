package ParkingDesign.ParkingStrategies;

import ParkingDesign.ParkingFloor;
import ParkingDesign.ParkingSpot;
import ParkingDesign.VehicleType;

import java.util.List;

public interface PrakingStrategy {

    ParkingSpot findSpot(List<ParkingFloor> floors, VehicleType type);
}
//“This allows us to plug in different spot allocation logics,
// without changing any other part of the system — following Strategy & Open/Closed Principles.”