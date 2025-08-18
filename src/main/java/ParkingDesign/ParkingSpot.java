package ParkingDesign;
import ParkingDesign.SpotUnAvailableException;

public class ParkingSpot {
    private final String spotId;
    private final VehicleType spotType;
    private Vehicle currentVehicle;

    public ParkingSpot (String spotId, VehicleType spotType){
        this.spotId = spotId;
        this.spotType = spotType;
    }

    public String getSpotId(){
        return spotId;
    }

    public VehicleType getSpotType(){
        return spotType;
    }

    //“The isAvailable() method abstracts the logic of checking whether a spot is occupied. This keeps the rest of the code cleaner,
    // readable, and adheres to the single responsibility principle.”
    public boolean isAvailable(){
        return currentVehicle == null;
    }

    public boolean assignVehicle(Vehicle vehicle){
        if(!isAvailable()) {
            throw new SpotUnAvailableException("Spot " + spotId + " is already occupied.");
        }
        if(vehicle.getType() != spotType)return false;
        this.currentVehicle = vehicle;
        return true;
    }

    public Vehicle getCurrentVehicle(){
        return currentVehicle;
    }

    public synchronized void removeVehicle() {
        this.currentVehicle = null;
    }

}
/*
The assignVehicle method first checks if the spot is available,then ensures the vehicle is of the correct type.
Only after these validations, it assigns the vehicle. This ensures safe and consistent parking behavior.
Returning a boolean gives flexibility to the caller to take action based on success or failure

Since parking spots can be accessed concurrently by multiple vehicles, I’ve marked assignVehicle() as synchronized to ensure thread safety.
This prevents race conditions where two vehicles might get assigned to the same spot.
 */
