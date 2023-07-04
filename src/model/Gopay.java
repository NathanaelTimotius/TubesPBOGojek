package model;

import java.time.LocalDate;

public class Gopay extends Transaction {
    private double topUpBalance;

    public Gopay(double topUpBalance, String personName, Service serviceID, PaymentMethod paymentMethod, Voucher voucher, double totalPrice, double totalDiscount, double priceAfterDiscount, LocalDate transactionDate, String transactionID, double adminFee) {
        super(personName, serviceID, paymentMethod, voucher, totalPrice, totalDiscount, priceAfterDiscount, transactionDate, transactionID, adminFee);
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
