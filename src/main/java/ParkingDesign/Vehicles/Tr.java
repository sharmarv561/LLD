package ParkingDesign.Vehicles;

import ParkingDesign.Vehicle;
import ParkingDesign.VehicleType;

public class Tr extends Vehicle {
    public Tr(String licensePlate, VehicleType type) {
        super(licensePlate, VehicleType.TRUCK);
    }
}
