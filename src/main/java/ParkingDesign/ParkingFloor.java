package ParkingDesign;

import java.util.ArrayList;
import java.util.HashMap;
import ParkingDesign.Exceptions.SpotNotFoundException;
import java.util.Map;

public class ParkingFloor {
    private final int floorNumber;
    private final Map<String , ParkingSpot> spotMap;//I chose a Map keyed by spotId for O(1) lookup of spots during operations like unparking or querying spot status.
                                                    // This simplifies logic and scales well.”

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.spotMap = new HashMap<>();
    }

    public int getFloorNumber(){
        return floorNumber;
    }

    public void addSpot(ParkingSpot spot) {
        spotMap.put(spot.getSpotId(), spot);
    }

    public ParkingSpot getAvailableSpot(VehicleType type){
        for(ParkingSpot spot : spotMap.values()){
            if(spot.getSpotType() == type && spot.isAvailable()){
                return spot;
            }
        }
        return null;
    }

    public boolean parkVehicle(Vehicle vehicle){
        ParkingSpot spot = getAvailableSpot(vehicle.getType());
        if(spot == null) return false;
        return spot.assignVehicle(vehicle);
    }

    public boolean unparkVehicle(String spotId){
        ParkingSpot spot = spotMap.get(spotId);
        if (spot == null) {
            throw new SpotNotFoundException("Spot ID " + spotId + " not found.");
        }
        if (spot.isAvailable()) {
            throw new SpotNotFoundException("Spot " + spotId + " is already empty.");
        }
        spot.removeVehicle();
        return true;
    }

    public int getAvailableSpotCount(VehicleType type){
        int count = 0;
        for(ParkingSpot spot : spotMap.values()){
            if(spot.getSpotType() == type && spot.isAvailable())count++;
        }
        return count;
    }
}

/*
I designed the ParkingFloor class to manage a set of parking spots. I used a single Map<String, ParkingSpot> for fast access and to keep the implementation clean.
 Methods like parkVehicle and unparkVehicle focus on encapsulating business logic and maintaining data consistency.
 I made sure to handle null cases and added defensive checks for robustness.
 For concurrency, I would synchronize spot-level operations as needed.
Overall, I balanced simplicity and correctness for an interview-ready solution.
 */
