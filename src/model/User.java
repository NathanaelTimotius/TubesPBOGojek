package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class User extends Person{
    private String email;
    private String userID;
    private String username;
    private String password;
    private String gender;
    private double totalBalance;
    private ArrayList<Transaction> transaction;
    private Region region;

    public User(String email, String userID, String username, String password, String gender, double totalBalance, ArrayList<Transaction> transaction, Region region, String name, String address, String phoneNumber, LocalDate birthDate) {
        super(name, address, phoneNumber, birthDate);
        this.email = email;
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.totalBalance = totalBalance;
        this.transaction = transaction;
        this.region = region;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public ArrayList<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(ArrayList<Transaction> transaction) {
        this.transaction = transaction;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", userID=" + userID + ", username=" + username + ", password=" + password + ", gender=" + gender + ", totalBalance=" + totalBalance + ", transaction=" + transaction + ", region=" + region + '}';
    }
    
}
