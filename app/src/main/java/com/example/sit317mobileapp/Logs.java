package com.example.sit317mobileapp;

public class Logs {
    //variables
    String Person;
    String Message;

    //constructor
    public Logs(String person, String message) {
        Person = person;
        Message = message;
    }

    //getters and setters
    public String getPerson() {
        return Person;
    }

    public void setPerson(String person) {
        Person = person;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
