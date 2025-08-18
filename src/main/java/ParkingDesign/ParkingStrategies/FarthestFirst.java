package ParkingDesign.ParkingStrategies;

import ParkingDesign.ParkingFloor;
import ParkingDesign.ParkingSpot;
import ParkingDesign.VehicleType;

import java.util.List;

public class FarthestFirst implements PrakingStrategy{
    @Override
    public ParkingSpot findSpot(List<ParkingFloor> floors, VehicleType type) {

        for (int i = floors.size() - 1; i >= 0; i--) {
            ParkingSpot spot = floors.get(i).getAvailableSpot(type);
            if (spot != null) return spot;
        }
        return null;
    }
}
