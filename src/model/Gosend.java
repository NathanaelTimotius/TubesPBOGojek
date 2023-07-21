package model;

import java.util.Date;

public class Gosend extends Transaction {
    private Driver driver;
    private double distance;
    private String item;
    private String terima;
    private int titikAntar;
    private int titikJemput;

    public Gosend() {

    }

    public Gosend(Driver driver, double distance, String item, String terima, int titikAntar, int titikJemput) {
        this.driver = driver;
        this.distance = distance;
        this.item = item;
        this.terima = terima;
        this.titikAntar = titikAntar;
        this.titikJemput = titikJemput;
    }

    public Gosend(Driver driver, double distance, String item, String terima, int titikAntar, int titikJemput, int userID, int serviceID, PaymentMethod paymentMethod, Voucher voucher, double totalPrice, double totalDiscount, double priceAfterDiscount, Date transactionDate, int transactionID, double adminFee, OrderStatus orderStatus) {
        super(userID, serviceID, paymentMethod, voucher, totalPrice, totalDiscount, priceAfterDiscount, transactionDate, transactionID, adminFee, orderStatus);
        this.driver = driver;
        this.distance = distance;
        this.item = item;
        this.terima = terima;
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTerima() {
        return terima;
    }

    public void setTerima(String terima) {
        this.terima = terima;
    }

    public int getTitikAntar() {
        return titikAntar;
    }

    public void setTitikAntar(int titikAntar) {
        this.titikAntar = titikAntar;
    }

    public int getTitikJemput() {
        return titikJemput;
    }

    public void setTitikJemput(int titikJemput) {
        this.titikJemput = titikJemput;
    }
    
}
