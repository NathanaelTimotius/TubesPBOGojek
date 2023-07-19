package model;

public class Voucher {
    private String voucherName;
    private int voucherID;
    private double discountPercentage;

    public Voucher() {

    }

    public Voucher(String voucherName, int voucherID, double discountPercentage) {
        this.voucherName = voucherName;
        this.voucherID = voucherID;
        this.discountPercentage = discountPercentage;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public int getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(int voucherID) {
        this.voucherID = voucherID;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "Voucher{" + "voucherName=" + voucherName + ", voucherID=" + voucherID + ", discountPercentage="
                + discountPercentage + '}';
    }

}
