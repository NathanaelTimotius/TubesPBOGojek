package model;

import java.util.Date;

public class Transaction implements Service{
    private int userID;
    private int serviceID;
    private PaymentMethod paymentMethod;
    private Voucher voucher;
    private double totalPrice;
    private double totalDiscount;
    private double priceAfterDiscount;
    private Date transactionDate;
    private int transactionID;
    private double adminFee;
    private OrderStatus orderStatus;
    
    public Transaction(){
        
    }
    
    public Transaction(int userID, int serviceID, PaymentMethod paymentMethod, Voucher voucher, double totalPrice, double totalDiscount, double priceAfterDiscount, Date transactionDate, int transactionID, double adminFee, OrderStatus orderStatus) {
        this.userID = userID;
        this.serviceID = serviceID;
        this.paymentMethod = paymentMethod;
        this.voucher = voucher;
        this.totalPrice = totalPrice;
        this.totalDiscount = totalDiscount;
        this.priceAfterDiscount = priceAfterDiscount;
        this.transactionDate = transactionDate;
        this.transactionID = transactionID;
        this.adminFee = adminFee;
        this.orderStatus = orderStatus;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public double getAdminFee() {
        return adminFee;
    }

    public void setAdminFee(double adminFee) {
        this.adminFee = adminFee;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Transaction{" + "userID=" + userID + ", serviceID=" + serviceID + ", paymentMethod=" + paymentMethod + ", voucher=" + voucher + ", totalPrice=" + totalPrice + ", totalDiscount=" + totalDiscount + ", priceAfterDiscount=" + priceAfterDiscount + ", transactionDate=" + transactionDate + ", transactionID=" + transactionID + ", adminFee=" + adminFee + '}';
    }
    
    
}
