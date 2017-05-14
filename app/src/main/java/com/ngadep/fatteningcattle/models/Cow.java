package com.ngadep.fatteningcattle.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cow {
    private String package_id;
    private String ear_tag;
    private String sex;
    private int weight;
    private Double price;
    private Long date;

    public Cow() {
        // Default constructor required for calls to DataSnapshot.getValue(Cow.class)
    }

    public Cow(String package_id, String ear_tag, String sex, int weight, Double price, Long date) {
        this.package_id = package_id;
        this.ear_tag = ear_tag;
        this.sex = sex;
        this.weight = weight;
        this.price = price;
        this.date = date;
    }

    public String getEar_tag() {
        return ear_tag;
    }

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    public void setEar_tag(String ear_tag) {
        this.ear_tag = ear_tag;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDate() {
        return SimpleDateFormat.getDateInstance().format(new Date(date));
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
