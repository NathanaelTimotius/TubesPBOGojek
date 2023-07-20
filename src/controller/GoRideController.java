package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;
import model.Region;
import model.Transaction;

public class GoRideController {
    private DatabaseHandler conn;

    public GoRideController(){
        this.conn = new DatabaseHandler();
    }

    public User getUserByUsername(String username){
        User user = null;
        try {
            conn.connect();
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                user = new User();
                user.setUserID(rs.getInt("id_user"));
                // region
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                // user.setBirthDate();
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
            String query = "INSERT INTO transaksi(serviceId, paymentMethod, totalPrice, adminFee, totalDiscount, finalPrice, transactionDate, status, id_user) VALUES(?,?,?,?,?,?,?,?,?)";
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
}