package com.thegloriousfountainministries.exp2.custom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by My HP on 3/31/2017.
 */

public class TrackModel {

    @SerializedName("title")
    private String titlee;
    @SerializedName("time")
    private String timee;
    @SerializedName("price")
    private String price;
    @SerializedName("trackurl")
    private String url;

    public TrackModel() {
    }

    public TrackModel(String details, String thumbnail, String price, String url) {

        this.titlee = details;
        this.timee = thumbnail;
        this.price = price;
        this.url = url;

    }

    public String getTitlee() {
        return titlee;
    }

    public String getTimee() {
        return timee;
    }

    public String getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }
}
