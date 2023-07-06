package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Admin;
import model.Driver;
import model.Region;
import model.Restaurant;
import model.Transaction;
import model.User;

public class Controller {
    DatabaseHandler conn = new DatabaseHandler();

    // SELECT ALL 
    public  ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM users";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("Id_User"));
                user.setName(rs.getString("Name"));
                user.setAddress(rs.getString("Address"));
                user.setPhoneNumber(rs.getString("Phone_number"));
                user.setBirthDate(rs.getDate("Birth_date"));
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setGender(rs.getString("Gender"));
                user.setTotalBalance(rs.getDouble("Total_balance"));
                user.setRegion(getRegion(rs.getInt("Id_Region")));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (users);
    }
    
    // SELECT ALL Transaction pada user tertentu
    public  ArrayList<Transaction> getAllTransactionPerUser(int Id_User) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM transaction WHERE id_user='" + Id_User + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setPersonName(rs.getString("person_name"));
//                transaction.setServiceID();
//                transaction.setPaymentMethod();
//                transaction.setVoucher();
                transaction.setTotalPrice(rs.getDouble("total_price"));
                transaction.setTotalDiscount(rs.getDouble("total_discount"));
                transaction.setPriceAfterDiscount(rs.getDouble("price_after_discount"));
                transaction.setTransactionDate(rs.getDate("transaction_date"));
                transaction.setTransactionID(rs.getInt("transaction_id"));
                transaction.setAdminFee(rs.getDouble("admin_fee"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (transactions);
    }

    // SELECT WHERE
    public User getUser(String name, String address) {
        conn.connect();
        String query = "SELECT * FROM users WHERE Name='" + name + "'&&Address='" + address + "'";
        User user = new User();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user.setUserID(rs.getInt("Id_User"));
                user.setName(rs.getString("Name"));
                user.setAddress(rs.getString("Address"));
                user.setPhoneNumber(rs.getString("Phone_number"));
                user.setBirthDate(rs.getDate("Birth_date"));
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setGender(rs.getString("Gender"));
                user.setTotalBalance(rs.getDouble("Total_balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (user);
    }
    
    public Driver getDriver(String name, String NIK) {
        conn.connect();
        String query = "SELECT * FROM users WHERE Name='" + name + "'&&NIK='" + NIK + "'";
        Driver driver = new Driver();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                driver.setDriverID(rs.getInt("Id_driver"));
                driver.setNIK(rs.getString("NIK"));
                driver.setPassword(rs.getString("Password"));
                driver.setIncome(rs.getDouble("Income"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (driver);
    }
    
    public Restaurant getRestaurant(String name, String password) {
        conn.connect();
        String query = "SELECT * FROM users WHERE Restaurant_name='" + name + "'&&Password='" + password + "'";
        Restaurant restaurant = new Restaurant();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                restaurant.setRestaurantID(rs.getInt("Id_Restoran"));
                restaurant.setRestaurantName(rs.getString("Restaurant_name"));
                restaurant.setPassword(rs.getString("Password"));
                restaurant.setIncome(rs.getDouble("Income"));
                restaurant.setPhoneNumber(rs.getString("phone_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (restaurant);
    }
    
    public Region getRegion(int Id_region){
        conn.connect();
        String query = "SELECT * FROM region WHERE Id_Restoran='" + Id_region + "'";
        Region region = new Region();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                region.setRegionName(rs.getString("region_name"));
                region.setRegionPosition(rs.getInt("region_position"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (region);
    }
    
    public Region getServiceID(int Id_region){
        conn.connect();
        String query = "SELECT * FROM region WHERE Id_Restoran='" + Id_region + "'";
        Region region = new Region();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                region.setRegionName(rs.getString("region_name"));
                region.setRegionPosition(rs.getInt("region_position"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (region);
    }
    
    
}
