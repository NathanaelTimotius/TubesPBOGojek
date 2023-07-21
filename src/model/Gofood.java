package model;

import java.util.ArrayList;
import java.util.Date;

public class Gofood extends Transaction {
    private Driver driver;
    private double distance;
    private String restaurantName;
    private int restaurantID;
    private ArrayList<Cart> cart;
    private double deliveryFee;
    private int titikAntar;

    public Gofood(){
        
    }

    public Gofood(Driver driver, double distance, String restaurantName, int restaurantID, ArrayList<Cart> cart, double deliveryFee, int titkAntar, int userID, int serviceID, PaymentMethod paymentMethod, Voucher voucher, double totalPrice, double totalDiscount, double priceAfterDiscount, Date transactionDate, int transactionID, double adminFee, OrderStatus orderStatus) {
        super(userID, serviceID, paymentMethod, voucher, totalPrice, totalDiscount, priceAfterDiscount, transactionDate, transactionID, adminFee, orderStatus);
        this.driver = driver;
        this.distance = distance;
        this.restaurantName = restaurantName;
        this.restaurantID = restaurantID;
        this.cart = cart;
        this.deliveryFee = deliveryFee;
        this.titikAntar = titkAntar;
    }

    public int getTitikAntar() {
        return titikAntar;
    }

    public void setTitikAntar(int titikAntar) {
        this.titikAntar = titikAntar;
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

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
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
