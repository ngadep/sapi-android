package com.ngadep.fatteningcattle.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
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

    protected User(Parcel in) {
        userName = in.readString();
        email = in.readString();
        displayName = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(email);
        dest.writeString(displayName);
    }
}
