package model;

import java.util.Date;

public class Gopay extends Transaction {
    private double topUpBalance;

    public Gopay(){
        
    }

    public Gopay(double topUpBalance, int userID, int serviceID, PaymentMethod paymentMethod, Voucher voucher, double totalPrice, double totalDiscount, double priceAfterDiscount, Date transactionDate, int transactionID, double adminFee, OrderStatus orderStatus) {
        super(userID, serviceID, paymentMethod, voucher, totalPrice, totalDiscount, priceAfterDiscount, transactionDate, transactionID, adminFee, orderStatus);
        this.topUpBalance = topUpBalance;
    }

    public double getTopUpBalance() {
        return topUpBalance;
    }

    public void setTopUpBalance(double topUpBalance) {
        this.topUpBalance = topUpBalance;
    }

    @Override
    public String toString() {
        return "Gopay{" + "topUpBalance=" + topUpBalance + '}';
    }

    
}
