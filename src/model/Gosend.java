package model;

import java.util.Date;

public class Gosend extends Transaction {
    private Driver driver;
    private double distance;
    private ItemType itemType;
    private String itemName;
    private Region titikAntar;
    private double deliveryFee;

    public Gosend(Driver driver, double distance, ItemType itemType, String itemName, Region titikAntar, double deliveryFee, String personName, Service serviceID, PaymentMethod paymentMethod, Voucher voucher, double totalPrice, double totalDiscount, double priceAfterDiscount, Date transactionDate, int transactionID, double adminFee) {
        super(personName, serviceID, paymentMethod, voucher, totalPrice, totalDiscount, priceAfterDiscount, transactionDate, transactionID, adminFee);
        this.driver = driver;
        this.distance = distance;
        this.itemType = itemType;
        this.itemName = itemName;
        this.titikAntar = titikAntar;
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

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Region getTitikAntar() {
        return titikAntar;
    }

    public void setTitikAntar(Region titikAntar) {
        this.titikAntar = titikAntar;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    @Override
    public String toString() {
        return "Gosend{" + "driver=" + driver + ", distance=" + distance + ", itemType=" + itemType + ", itemName=" + itemName + ", titikAntar=" + titikAntar + ", deliveryFee=" + deliveryFee + '}';
    }

}
