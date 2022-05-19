package com.example.sit317mobileapp;

public class Login {
    //variables
    private String Email;
    private String Username;
    private String Password;

    //getters and setters
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    private String Full_Name;

    //constructor
    public Login(String email, String username, String password, String full_Name) {
        Email = email;
        Username = username;
        Password = password;
        Full_Name = full_Name;
    }
}
