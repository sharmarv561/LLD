package ParkingDesign;

public abstract class Vehicle {
    private final String licensePlate;
    protected final VehicleType  type;

    public Vehicle(String licensePlate, VehicleType type){
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getType() {
        return type;
    }
}
/*
I’ve used private fields to follow the principle of encapsulation, which helps restrict direct access to the class’s internal state.
This also makes it easy to add validation or logic later if needed.
I expose read-only access through getters to allow safe access from other parts of the code, including subclasses if necessary.”

Optional:

“I avoid protected unless my subclass really needs to directly modify the field — which is rare and often breaks encapsulation.
 */