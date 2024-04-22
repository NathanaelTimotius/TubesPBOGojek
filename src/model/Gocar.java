package model;

import java.util.Date;

public class Gocar extends Transaction {
    private Driver driver;
    private double distance;
    private int titikAntar;
    private int titikJemput;

    public Gocar() {

    }

    public Gocar(Driver driver, double distance, int titikAntar, int titikJemput, double deliveryFee, int userID,
            int serviceID, PaymentMethod paymentMethod, Voucher voucher, double totalPrice, double totalDiscount,
            double priceAfterDiscount, Date transactionDate, int transactionID, double adminFee,
            OrderStatus orderStatus) {
        super(userID, serviceID, paymentMethod, voucher, totalPrice, totalDiscount, priceAfterDiscount, transactionDate,
                transactionID, adminFee, orderStatus);
        this.driver = driver;
        this.distance = distance;
        this.titikAntar = titikAntar;
        this.titikJemput = titikJemput;
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

    public int getTitikAntar() {
        return titikAntar;
    }

    public void setTitikAntar(int titikAntar) {
        this.titikAntar = titikAntar;
    }

    public void setTitikJemput(int titikJemput) {
        this.titikJemput = titikJemput;
    }

    public int getTitikJemput() {
        return titikJemput;
    }

    public String toString() {
        return "Gojek{" + "driver=" + driver + ", distance=" + distance + ", titikAntar=" + titikAntar
                + ", titikJemput=" + titikJemput + ", deliveryFee=" + '}';
    }
}
