package ParkingDesign.Vehicles;

import ParkingDesign.Vehicle;
import ParkingDesign.VehicleType;

public class Bike extends Vehicle {
    public Bike(String licensePlate, VehicleType type) {
        super(licensePlate, VehicleType.BIKE);
    }
}
