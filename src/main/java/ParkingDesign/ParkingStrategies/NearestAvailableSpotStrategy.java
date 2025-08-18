package ParkingDesign.ParkingStrategies;

import ParkingDesign.ParkingFloor;
import ParkingDesign.ParkingSpot;
import ParkingDesign.VehicleType;

import java.util.List;

public class NearestAvailableSpotStrategy implements PrakingStrategy {


    @Override
    public ParkingSpot findSpot(List<ParkingFloor> floors, VehicleType type) {
        for(ParkingFloor floor : floors){
            ParkingSpot spot = floor.getAvailableSpot(type);
            if(spot!=null)return spot;
        }
        return null;
    }
}
