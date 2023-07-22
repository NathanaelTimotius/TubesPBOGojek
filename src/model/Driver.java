package model;

import java.sql.Date;

public class Driver extends Person {
    private String NIK;
    private String password;
    private int driverID;
    private double income;
    private Vehicle vehicle;
    private boolean available;

    public Driver() {

    }

    public Driver(String NIK, String password, int driverID, double income, Vehicle vehicle, String name,
            String address, String phoneNumber, Date birthDate, boolean available) {
        super(name, address, phoneNumber, birthDate);
        this.NIK = NIK;
        this.password = password;
        this.driverID = driverID;
        this.income = income;
        this.vehicle = vehicle;
        this.available = available;
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

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Driver{" + "NIK=" + NIK + ", password=" + password + ", driverID=" + driverID + ", income=" + income
                + ", vehicle=" + vehicle + '}';
    }
}
