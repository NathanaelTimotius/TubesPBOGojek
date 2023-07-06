package model;

import java.util.ArrayList;

public class Restaurant {
    private int restaurantID;
    private String restaurantName;
    private String password;
    private String phoneNumber;
    private Region region;
    private ArrayList<Menu> listMenu;
    private double income;

    public Restaurant (){
        
    }
    
    public Restaurant(int restaurantID, String restaurantName, String password, String phoneNumber, Region region, ArrayList<Menu> listMenu, double income) {
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.region = region;
        this.listMenu = listMenu;
        this.income = income;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public ArrayList<Menu> getListMenu() {
        return listMenu;
    }

    public void setListMenu(ArrayList<Menu> listMenu) {
        this.listMenu = listMenu;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "restaurantID=" + restaurantID + ", restaurantName=" + restaurantName + ", password=" + password + ", phoneNumber=" + phoneNumber + ", region=" + region + ", listMenu=" + listMenu + ", income=" + income + '}';
    }
    
    
}
