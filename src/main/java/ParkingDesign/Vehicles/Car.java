package ParkingDesign.Vehicles;

import ParkingDesign.Vehicle;
import ParkingDesign.VehicleType;

public class Car extends Vehicle {
    public Car(String licensePlate, VehicleType type) {
        super(licensePlate, VehicleType.CAR);
    }
}
