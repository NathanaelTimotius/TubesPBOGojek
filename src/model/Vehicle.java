package model;

public class Vehicle {
    private VehicleType vehicleType;
    private String model;
    private String numberPlate;

    public Vehicle() {

    }

    public Vehicle(VehicleType vehicleType, String model, String numberPlate) {
        this.vehicleType = vehicleType;
        this.model = model;
        this.numberPlate = numberPlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "vehicleType=" + vehicleType + ", model=" + model + ", numberPlate=" + numberPlate + '}';
    }
    
    
}
