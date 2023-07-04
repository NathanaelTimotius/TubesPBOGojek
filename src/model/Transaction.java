package model;

import java.time.LocalDate;

public class Transaction implements Service{
    private String personName;
    private Service serviceID;
    private PaymentMethod paymentMethod;
    private Voucher voucher;
    private double totalPrice;
    private double totalDiscount;
    private double priceAfterDiscount;
    private LocalDate transactionDate;
    private String transactionID;
    private double adminFee;

    public Transaction(String personName, Service serviceID, PaymentMethod paymentMethod, Voucher voucher, double totalPrice, double totalDiscount, double priceAfterDiscount, LocalDate transactionDate, String transactionID, double adminFee) {
        this.personName = personName;
        this.serviceID = serviceID;
        this.paymentMethod = paymentMethod;
        this.voucher = voucher;
        this.totalPrice = totalPrice;
        this.totalDiscount = totalDiscount;
        this.priceAfterDiscount = priceAfterDiscount;
        this.transactionDate = transactionDate;
        this.transactionID = transactionID;
        this.adminFee = adminFee;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Service getServiceID() {
        return serviceID;
    }

    public void setServiceID(Service serviceID) {
        this.serviceID = serviceID;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(double priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public double getAdminFee() {
        return adminFee;
    }

    public void setAdminFee(double adminFee) {
        this.adminFee = adminFee;
    }

    @Override
    public String toString() {
        return "Transaction{" + "personName=" + personName + ", serviceID=" + serviceID + ", paymentMethod=" + paymentMethod + ", voucher=" + voucher + ", totalPrice=" + totalPrice + ", totalDiscount=" + totalDiscount + ", priceAfterDiscount=" + priceAfterDiscount + ", transactionDate=" + transactionDate + ", transactionID=" + transactionID + ", adminFee=" + adminFee + '}';
    }
    
    
}
