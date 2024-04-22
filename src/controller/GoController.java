package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;
import model.Voucher;
import model.Transaction;

public class GoController {
    private DatabaseHandler conn;

    public GoController(){
        this.conn = new DatabaseHandler();
    }
    
    public User getUserByUsername(String username) {
        User user = null;
        try {
            conn.connect();
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                user = new User();
                user.setUserID(rs.getInt("id_user"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setBirthDate(rs.getDate("birthDate"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setTotalBalance(rs.getDouble("totalBalance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return user;
    }

    public boolean updateGoPay(String username, double total){
        try {
            conn.connect();
            String query = "UPDATE users SET totalBalance=" + total + " WHERE username='" + username + "'";
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }

    public Voucher getVoucher(String name){
        Voucher voucher = null;
        try {
            conn.connect();
            String query = "SELECT * FROM voucher WHERE voucherName = '" + name + "'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                voucher = new Voucher();
                voucher.setVoucherName(rs.getString("voucherName"));
                voucher.setVoucherID(rs.getInt("id_voucher"));
                voucher.setDiscountPercentage(rs.getDouble("discountPercentage"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return voucher;
    }
    
    public int getTransactionID(Transaction transaction) {
        try {
            conn.connect();
            String query = "SELECT id_transaksi FROM transaksi WHERE serviceId = ? AND paymentMethod = ? AND finalPrice = ? AND transactionDate = ? ORDER BY id_transaksi DESC";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, transaction.getServiceID());
            stmt.setString(2, transaction.getPaymentMethod().name());
            stmt.setDouble(3, transaction.getPriceAfterDiscount());
            stmt.setDate(4, new java.sql.Date(transaction.getTransactionDate().getTime()));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_transaksi");
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            conn.disconnect();
        }
    }
}