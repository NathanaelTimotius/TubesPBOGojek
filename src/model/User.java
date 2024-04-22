package model;

import java.sql.Date;
import java.util.ArrayList;

public class User extends Person{
    private String email;
    private int userID;
    private String username;
    private String password;
    private String gender;
    private double totalBalance;
    private ArrayList<Transaction> transaction;
    private Region region;
    
    public User(){
        
    }
    
    public User(String email, int userID, String username, String password, String gender, double totalBalance, ArrayList<Transaction> transaction, Region region, String name, String address, String phoneNumber, Date birthDate) {
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



    public User(String name, String address, String phoneNumber, String username2, String email2, String password2,
            String gender2, Date birthday) {
        super(name, address, phoneNumber, birthday);
        this.username = username2;
        this.email = email2;
        this.password = password2;
        this.gender = gender2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
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
