package com.ngadep.fatteningcattle.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Package {
    private String name;
    private String location;
    private int type;
    private boolean active;

    public Package() {
        // Default constructor required for calls to DataSnapshot.getValue(Package.class)
    }

    public Package(String name, String location, int type, boolean active) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.active = active;
    }

    public Package(String name, String location, int type) {
        this.name = name;
        this.location = location;
        this.type = type;
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
        result.put("name", this.name);
        result.put("location", this.location);
        result.put("type", this.type);
        result.put("active", this.active);

        return result;
    }
}
