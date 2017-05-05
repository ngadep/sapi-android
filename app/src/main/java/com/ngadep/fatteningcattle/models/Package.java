package com.ngadep.fatteningcattle.models;

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
}
