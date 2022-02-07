package com.example.loginregistrtionformapp;

public class Mclass {
    private String gender;
    private String fName;
    private String lName;
    private String email;

    public Mclass(String fName, String lName, String gender, String email) {
        this.gender = gender;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

