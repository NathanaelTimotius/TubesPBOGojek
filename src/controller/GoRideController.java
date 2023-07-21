package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import model.User;
import model.Voucher;
import model.Goride;
import model.Region;
import model.Transaction;

public class GoRideController {
    private DatabaseHandler conn;

    public GoRideController(){
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

    public ArrayList<Region> getAllRegions(){
        ArrayList<Region> regions = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM region";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Region region = new Region();
                region.setRegionName(rs.getString("regionName"));
                region.setRegionPosition(rs.getInt("regionPosition"));

                regions.add(region);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return regions;
    }

    public boolean insertGoRideTransaction(Transaction transaction){
        try {
            conn.connect();
            String query = "INSERT INTO transaksi(serviceId, paymentMethod, totalPrice, adminFee, totalDiscount, finalPrice, transactionDate, status, id_user, id_voucher) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, transaction.getServiceID());
            stmt.setString(2, transaction.getPaymentMethod().name());
            stmt.setDouble(3, transaction.getTotalPrice());
            stmt.setDouble(4, transaction.getAdminFee());
            stmt.setDouble(5, transaction.getTotalDiscount());
            stmt.setDouble(6, transaction.getPriceAfterDiscount());
            Date transactionDate = new Date(transaction.getTransactionDate().getTime());
            stmt.setDate(7, transactionDate);
            stmt.setString(8, transaction.getOrderStatus().name());
            stmt.setInt(9, transaction.getUserID());

            if (transaction.getVoucher() == null) {
                stmt.setNull(10, Types.INTEGER);
            } else {
                stmt.setInt(10, transaction.getVoucher().getVoucherID());
            }

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
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


    public boolean insertToGoRide(Goride goride){
        try {
            conn.connect();
            String query = "INSERT INTO goride(id_transaksi, id_region_antar, id_region_jemput, distance) VALUES(?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, goride.getTransactionID());
            stmt.setInt(2, goride.getTitikAntar());
            stmt.setInt(3, goride.getTitikJemput());
            stmt.setDouble(4, goride.getDistance());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
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
}