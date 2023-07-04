package model;

import java.time.LocalDate;

public class Admin extends Person {
    private String username;
    private String password; 
    private double income;

    public Admin(String name, String username, String password, double income, String address, String phoneNumber, LocalDate birthDate) {
        super(name, address, phoneNumber, birthDate);
        this.username = username;
        this.password = password;
        this.income = income;
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

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "Admin{" + "username=" + username + ", password=" + password + ", income=" + income + '}';
    }

    
    
}
