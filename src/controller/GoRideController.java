package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;
import model.Region;

public class GoRideController {
    private DatabaseHandler conn;

    public GoRideController(){
        this.conn = new DatabaseHandler();
    }

    public User getUserByUsername(String username){
        User user = null;
        try {
            conn.connect();
            String query = "SELECT * FROM user WHERE username = '" + username + "'";
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
}