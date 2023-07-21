package model;

import java.util.Date;

public class Gosend extends Transaction {
    private Driver driver;
    private double distance;
    private String item;// Nama Item
    private String receiverName; // Nama penerima
    private int pickupLocation; // Titik jemput
    private int destination; // Titik antar

    public Gosend() {

    }

    public Gosend(Driver driver, double distance, String item, String receiverName, int pickupLocation, int destination) {
        this.driver = driver;
        this.distance = distance;
        this.item = item;
        this.receiverName = receiverName;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
    }

    public Gosend(Driver driver, double distance, String item, String receiverName, int pickupLocation, int destination, int userID, int serviceID, PaymentMethod paymentMethod, Voucher voucher, double totalPrice, double totalDiscount, double priceAfterDiscount, Date transactionDate, int transactionID, double adminFee, OrderStatus orderStatus) {
        super(userID, serviceID, paymentMethod, voucher, totalPrice, totalDiscount, priceAfterDiscount, transactionDate, transactionID, adminFee, orderStatus);
        this.driver = driver;
        this.distance = distance;
        this.item = item;
        this.receiverName = receiverName;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public int getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(int pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }
    
    @Override
    public String toString() {
        return "Gosend{" +
                "driver=" + driver +
                ", receiverName='" + receiverName +
                ", item='" + item +
                ", distance=" + distance +
                ", pickupLocation=" + pickupLocation +
                ", destination=" + destination +
                '}';
    }
}
