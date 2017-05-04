package com.ngadep.fatteningcattle.models;

public class User {
    public String userName;
    public String email;
    public String displayName;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public User(String userName, String email, String displayName) {
        this.userName = userName;
        this.email = email;
        this.displayName = displayName;
    }
}
