package com.thegloriousfountainministries.exp2.custom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by My HP on 3/31/2017.
 */

public class BooksModel {
    @SerializedName("id")
    private String id;
    @SerializedName("description")
    private String details;
    @SerializedName("image")
    private String thumbnail;
    @SerializedName("price")
    private String price;
    @SerializedName("title")
    private String book_title;
    @SerializedName("albumurl")
    private String desc_store;

    public BooksModel() {
    }

    public BooksModel(String id, String details, String thumbnail, String desc_store, String price, String book_title) {
        this.id = id;
        this.details = details;
        this.thumbnail = thumbnail;
        this.desc_store = desc_store;
        this.price = price;
        this.book_title = book_title;
    }

    public String getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDesc_store() {
        return desc_store;
    }

    public String getPrice() {
        return price;
    }

    public String getBook_title() {
        return book_title;
    }
}
