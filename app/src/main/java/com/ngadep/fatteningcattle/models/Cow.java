package com.ngadep.fatteningcattle.models;

public class Cow {
    public String earTag;
    public String sex;
    public int weight;

    public Cow(){
        // Default constructor required for calls to DataSnapshot.getValue(Cow.class)
    }

    public Cow(String earTag, String sex, int weight) {
        this.earTag = earTag;
        this.sex = sex;
        this.weight = weight;
    }
}
