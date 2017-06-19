package com.ngadep.fatteningcattle.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Cow implements Parcelable{
    private String package_id;
    private String ear_tag;
    private String sex;
    private int weight;
    private Long date;
    @Exclude
    private Long price;

    public Cow() {
        // Default constructor required for calls to DataSnapshot.getValue(Cow.class)
    }

    public Cow(String package_id, String ear_tag, String sex, int weight, Long date) {
        this.package_id = package_id;
        this.ear_tag = ear_tag;
        this.sex = sex;
        this.weight = weight;
        this.date = date;
    }

    protected Cow(Parcel in) {
        package_id = in.readString();
        ear_tag = in.readString();
        sex = in.readString();
        weight = in.readInt();
    }

    public static final Creator<Cow> CREATOR = new Creator<Cow>() {
        @Override
        public Cow createFromParcel(Parcel in) {
            return new Cow(in);
        }

        @Override
        public Cow[] newArray(int size) {
            return new Cow[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(package_id);
        dest.writeString(ear_tag);
        dest.writeString(sex);
        dest.writeInt(weight);
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
        return (double) (price * weight);
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDate() {
        return SimpleDateFormat.getDateInstance().format(new Date(date));
    }

    public Long getLongDate() {
        return this.date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("package_id", this.package_id);
        result.put("ear_tag", this.ear_tag);
        result.put("sex", this.sex);
        result.put("weight", this.weight);
        result.put("date", this.date);

        return result;
    }
}
