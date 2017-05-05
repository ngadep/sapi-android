package com.ngadep.fatteningcattle.models;

public class Cow {
    private String earTag;
    private String sex;
    private int weight;

    public Cow() {
        // Default constructor required for calls to DataSnapshot.getValue(Cow.class)
    }

    public Cow(String earTag, String sex, int weight) {
        this.earTag = earTag;
        this.sex = sex;
        this.weight = weight;
    }

    public String getEarTag() {
        return earTag;
    }

    public void setEarTag(String earTag) {
        this.earTag = earTag;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
