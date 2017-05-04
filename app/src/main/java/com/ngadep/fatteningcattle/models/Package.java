package com.ngadep.fatteningcattle.models;

public class Package {
    public String name;
    public String location;
    public int type;

    public Package() {
        // Default constructor required for calls to DataSnapshot.getValue(Package.class)
    }

    public Package(String name, String location, int type) {
        this.name = name;
        this.location = location;
        this.type = type;
    }
}
