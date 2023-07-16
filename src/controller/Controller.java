package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.*;

public class Controller {
    DatabaseHandler conn = new DatabaseHandler();
    
    // SELECT ALL 
    public  ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM user";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("id_User"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setBirthDate(rs.getDate("birthDate"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setTotalBalance(rs.getDouble("totalBalance"));
                user.setRegion(getRegion(rs.getInt("id_Region")));
                user.setTransaction(getAllTransactionPerUser(rs.getInt("id_transaksi")));
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
        String query = "SELECT * FROM transaksi WHERE id_user='" + Id_User + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionID(rs.getInt("id_transaksi"));
//                transaction.setVoucher();
//                transaction.setServiceID(rs.getInt("id_transaksi"));
//                transaction.setPaymentMethod();
                transaction.setTotalPrice(rs.getDouble("totalPrice"));
                transaction.setAdminFee(rs.getDouble("adminFee"));
                transaction.setTotalDiscount(rs.getDouble("totalDiscount"));
                transaction.setPriceAfterDiscount(rs.getDouble("finalPrice"));
                transaction.setTransactionDate(rs.getDate("transactionDate"));
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
    
    public ArrayList<Restaurant> getAllRestoran() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM restoran";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Restaurant resto = new Restaurant();
                resto.setRestaurantID(rs.getInt("id_restoran"));
                resto.setRestaurantName(rs.getString("restaurantName"));
                resto.setPassword(rs.getString("password"));
                resto.setPhoneNumber(rs.getString("phoneNumber"));
                resto.setRegion(getRegion(rs.getInt("id_region")));
                resto.setListMenu(getMenuRestoran(rs.getInt("id_restoran")));
                resto.setIncome(rs.getDouble("income"));
                restaurants.add(resto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (restaurants);
    }
    
    public ArrayList<Menu> getMenuRestoran(int idRestoran){
        ArrayList<Menu> menus = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM restoran_menu";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                
                if (rs.getInt("id_restoran") == idRestoran){
                    Menu menu = getMenu(rs.getInt("id_menu"));
                    menus.add(menu);
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (menus);
    }
    
    public Menu getMenu(int idMenu){
        conn.connect();
        String query = "SELECT * FROM menu WHERE id_menu ='" + idMenu + "'";
        Menu menu = new Menu();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                menu.setMenuName(rs.getString("menuName"));
                if (rs.getString("menuType").equals(MenuType.FOOD)){
                    menu.setMenuType(MenuType.FOOD);
                } else {
                    menu.setMenuType(MenuType.DRINK);
                }
                menu.setPrice(rs.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (menu);
    }
    
    public Region getRegion(int Id_region){
        conn.connect();
        String query = "SELECT * FROM region WHERE id_region ='" + Id_region + "'";
        Region region = new Region();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                region.setRegionName(rs.getString("regionName"));
                region.setRegionPosition(rs.getInt("regionPosition"));
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
