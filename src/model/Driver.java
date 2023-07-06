package model;

import java.util.Date;

public class Driver extends Person {
    private String NIK;
    private String password;
    private int driverID;
    private double income;
    private Vehicle vehicle;
    
    public Driver(){
        
    }
    
    public Driver(String NIK, String password, int driverID, double income, Vehicle vehicle, String name, String address, String phoneNumber, Date birthDate) {
        super(name, address, phoneNumber, birthDate);
        this.NIK = NIK;
        this.password = password;
        this.driverID = driverID;
        this.income = income;
        this.vehicle = vehicle;
    }

    

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Driver{" + "NIK=" + NIK + ", password=" + password + ", driverID=" + driverID + ", income=" + income + ", vehicle=" + vehicle + '}';
    }
}
