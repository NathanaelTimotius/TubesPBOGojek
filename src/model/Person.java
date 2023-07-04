package model;

import java.time.LocalDate;

public abstract class Person {
    private String name;
    private String address;
    private String phoneNumber;
    private LocalDate birthDate;

    public Person(String name, String address, String phoneNumber, LocalDate birthDate) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + ", birthDate=" + birthDate + '}';
    }
    
}
