package com.thegloriousfountainministries.exp2.custom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by My HP on 3/31/2017.
 */

public class CartQuery {
    @SerializedName("title")
    private String titlee;
    @SerializedName("image")
    private String thumbnail2;
    @SerializedName("price")
    private String price3;
    @SerializedName("type")
    private String type;
    @SerializedName("dataurl")
    String dataUrl;
    @SerializedName("user_id")
    String user;

    public CartQuery() {

    }

    public CartQuery(String titlee, String thumbnail2, String price3, String type, String dataUrl, String user) {
        this.titlee = titlee;
        this.thumbnail2 = thumbnail2;
        this.price3 = price3;
        this.type = type;
        this.dataUrl = dataUrl;
        this.user = user;
    }

    public String getTitlee() {
        return titlee;
    }

    public String getThumbnail2() {
        return thumbnail2;
    }

    public String getPrice3() {
        return price3;
    }

    public String getType() {
        return type;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public String getUser() {
        return user;
    }
}
