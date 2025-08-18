package ParkingDesign;

import ParkingDesign.ParkingStrategies.NearestAvailableSpotStrategy;
import ParkingDesign.Vehicles.Car;
import ParkingDesign.feeStrategies.FlatRateStrategy;
import ParkingDesign.feeStrategies.VehicleBasedStrategy;

public class ParkingDemo {
    public static void main(String[] args) {
        // Create Parking Lot
        ParkingLot parkingLot = ParkingLot.getInstance();

        // Set strategies
        parkingLot.setFeeStrategy(new FlatRateStrategy(50.0));
        parkingLot.setParkingStrategy(new NearestAvailableSpotStrategy());

        // Add 1 floor
        ParkingFloor floor1 = new ParkingFloor(1);

        // Add spots to the floor
        floor1.addSpot(new ParkingSpot("C1", VehicleType.CAR));
        floor1.addSpot(new ParkingSpot("B1", VehicleType.BIKE));
        floor1.addSpot(new ParkingSpot("T1", VehicleType.TRUCK));

        parkingLot.addFloor(floor1);

        // Create a vehicle
        Vehicle car1 = new Car("CAR123", VehicleType.CAR);

        // Park the vehicle
        Ticket ticket = parkingLot.parkVehicle(car1);
        if (ticket != null) {
            System.out.println("Vehicle parked. Ticket ID: " + ticket.getTicketId());
        } else {
            System.out.println("Parking failed.");
        }

        // Unpark the vehicle
        double fee = parkingLot.unparkVehicle(ticket.getTicketId());
        if (fee >= 0) {
            System.out.println("Vehicle unparked. Fee: $" + fee);
        } else {
            System.out.println("Unparking failed.");
        }
    }
}
