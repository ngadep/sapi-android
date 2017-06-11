package com.ngadep.fatteningcattle.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Package implements Parcelable {
    private String uid;
    private String name;
    private String location;
    private int type;
    private boolean active;

    public Package() {
        // Default constructor required for calls to DataSnapshot.getValue(Package.class)
    }

    public Package(String uid, String name, String location, int type, boolean active) {
        this.uid = uid;
        this.name = name;
        this.location = location;
        this.type = type;
        this.active = active;
    }

    protected Package(Parcel in) {
        uid = in.readString();
        name = in.readString();
        location = in.readString();
        type = in.readInt();
        active = in.readByte() != 0;
    }

    public static final Creator<Package> CREATOR = new Creator<Package>() {
        @Override
        public Package createFromParcel(Parcel in) {
            return new Package(in);
        }

        @Override
        public Package[] newArray(int size) {
            return new Package[size];
        }
    };

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", this.uid);
        result.put("name", this.name);
        result.put("location", this.location);
        result.put("type", this.type);
        result.put("active", this.active);

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeInt(type);
        dest.writeByte((byte) (active ? 1 : 0));
    }
}
