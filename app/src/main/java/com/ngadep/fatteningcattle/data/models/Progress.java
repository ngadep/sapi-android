package com.ngadep.fatteningcattle.data.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Progress {
    private int month;
    private Long date;
    private int weight;
    private Double price;

    public Progress() {
        // Default constructor required for calls to DataSnapshot.getValue(Progress.class)
    }

    public Progress(int month, Long date, int weight, Double price) {
        this.month = month;
        this.date = date;
        this.weight = weight;
        this.price = price;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getDate() {
        return SimpleDateFormat.getDateInstance().format(new Date(date));
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
