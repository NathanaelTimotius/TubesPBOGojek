package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Gofood extends Transaction {
    private Driver driver;
    private double distance;
    private String restaurantName;
    private String restaurantID;
    private ArrayList<Cart> cart;
    private double deliveryFee;

    public Gofood(Driver driver, double distance, String restaurantName, String restaurantID, ArrayList<Cart> cart, double deliveryFee, String personName, Service serviceID, PaymentMethod paymentMethod, Voucher voucher, double totalPrice, double totalDiscount, double priceAfterDiscount, LocalDate transactionDate, String transactionID, double adminFee) {
        super(personName, serviceID, paymentMethod, voucher, totalPrice, totalDiscount, priceAfterDiscount, transactionDate, transactionID, adminFee);
        this.driver = driver;
        this.distance = distance;
        this.restaurantName = restaurantName;
        this.restaurantID = restaurantID;
        this.cart = cart;
        this.deliveryFee = deliveryFee;
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public ArrayList<Cart> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Cart> cart) {
        this.cart = cart;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    @Override
    public String toString() {
        return "Gofood{" + "driver=" + driver + ", distance=" + distance + ", restaurantName=" + restaurantName + ", restaurantID=" + restaurantID + ", cart=" + cart + ", deliveryFee=" + deliveryFee + '}';
    }
    
}
