package com.ngadep.fatteningcattle.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.text.SimpleDateFormat;
import java.util.Date;

@IgnoreExtraProperties
public class Progress {
    private String cow_id;
    private Long date;
    private int weight;
    @Exclude
    private Long price;

    public Progress() {
        // Default constructor required for calls to DataSnapshot.getValue(Progress.class)
    }

    public Progress(String cow_id, Long date, int weight) {
        this.cow_id = cow_id;
        this.date = date;
        this.weight = weight;
    }

    public Long getDate() {
        return this.date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCow_id() {
        return cow_id;
    }

    public void setCow_id(String cow_id) {
        this.cow_id = cow_id;
    }

    @Exclude
    public String getFormatDate() {
        return SimpleDateFormat.getDateInstance().format(new Date(date));
    }

    @Exclude
    public void setPrice(Long price) {
        this.price = price;
    }
    @Exclude
    public Double getPrice() {
        return (double) (price * weight);
    }
}
