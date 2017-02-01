package com.example.mvp.login;

/**
 * Created by laurent on 1/30/17.
 */

public class User {
    private int id;
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() {
        return lastName;
    }
}
