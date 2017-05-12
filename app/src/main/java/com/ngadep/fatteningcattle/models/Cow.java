package com.ngadep.fatteningcattle.models;

import java.util.Date;

public class Cow {
    private String earTag;
    private String sex;
    private int weight;
    private Long price;
    private Date date;

    public Cow() {
        // Default constructor required for calls to DataSnapshot.getValue(Cow.class)
    }

    public Cow(String earTag, String sex, int weight, Long price, Date date) {
        this.earTag = earTag;
        this.sex = sex;
        this.weight = weight;
        this.price = price;
        this.date = date;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
