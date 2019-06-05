package com.thegloriousfountainministries.exp2.custom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by My HP on 3/31/2017.
 */

public class PaidModel {

    @SerializedName("title")
    private String book_title;

    @SerializedName("dataurl")
    private String urlData;


    public PaidModel() {
    }

    public PaidModel(String book_title, String url) {
        this.book_title = book_title;
        this.urlData = url;
    }

    public String getBook_title() {
        return book_title;
    }
    public String getUrlData(){
        return urlData;
    }
}
