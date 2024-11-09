package com.example.addemployee;

public class Employee {
    private String name ;
    private String date ;
    private String email ;
    private String phone ;
    private String id ;
    private String gender ;

    public Employee(String date, String name, String id, String email, String phone, String gender) {
        this.date = date;
        this.name = name;
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    public Employee() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
