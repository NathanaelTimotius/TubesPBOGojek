package model;

import java.util.Date;

public class Gojek extends Transaction {
    private Driver driver;
    private double distance;
    private Region titikAntar;
    private Region titikJemput;
    private double deliveryFee;

    public Gojek(Driver driver, double distance, Region titikAntar, Region titikJemput, double deliveryFee, String personName, Service serviceID, PaymentMethod paymentMethod, Voucher voucher, double totalPrice, double totalDiscount, double priceAfterDiscount, Date transactionDate, int transactionID, double adminFee) {
        super(personName, serviceID, paymentMethod, voucher, totalPrice, totalDiscount, priceAfterDiscount, transactionDate, transactionID, adminFee);
        this.driver = driver;
        this.distance = distance;
        this.titikAntar = titikAntar;
        this.titikJemput = titikJemput;
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

    public Region getTitikAntar() {
        return titikAntar;
    }

    public void setTitikAntar(Region titikAntar) {
        this.titikAntar = titikAntar;
    }

    public Region getTitikJemput() {
        return titikJemput;
    }

    public void setTitikJemput(Region titikJemput) {
        this.titikJemput = titikJemput;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    @Override
    public String toString() {
        return "Gojek{" + "driver=" + driver + ", distance=" + distance + ", titikAntar=" + titikAntar + ", titikJemput=" + titikJemput + ", deliveryFee=" + deliveryFee + '}';
    }
}
