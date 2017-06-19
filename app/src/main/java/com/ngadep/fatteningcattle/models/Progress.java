package com.ngadep.fatteningcattle.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.text.SimpleDateFormat;
import java.util.Date;

@IgnoreExtraProperties
public class Progress {
    private Long date;
    private int weight;

    public Progress() {
        // Default constructor required for calls to DataSnapshot.getValue(Progress.class)
    }

    public Progress(Long date, int weight) {
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

    @Exclude
    public String getFormatDate() {
        return SimpleDateFormat.getDateInstance().format(new Date(date));
    }

}
