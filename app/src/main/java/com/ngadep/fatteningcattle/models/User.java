package com.ngadep.fatteningcattle.models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String userName;
    private String email;
    private boolean admin;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public User(String userName, String email, boolean admin) {
        this.userName = userName;
        this.email = email;
        this.admin = admin;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    protected User(Parcel in) {
        userName = in.readString();
        email = in.readString();
        admin = in.readByte() != 0x00;
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
        dest.writeByte((byte) (admin ? 0x01 : 0x00));
    }
}
