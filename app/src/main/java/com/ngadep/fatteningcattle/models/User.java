package com.ngadep.fatteningcattle.models;

public class User {
    private String userName;
    private String email;
    private String displayName;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
        this.displayName = userName;
    }

    public User(String userName, String email, String displayName) {
        this.userName = userName;
        this.email = email;
        this.displayName = displayName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
