package com.thegloriousfountainministries.exp2.custom;

/**
 * Created by My HP on 3/31/2017.
 */

public class CartModel {
    private String title3;
    private String thumbnail2;
    private String price3;
    private String qua;

    public CartModel() {

    }

    public CartModel(String title3, String thumbnail2, String price3, String qua) {
        this.title3 = title3;
        this.thumbnail2 = thumbnail2;
        this.price3 = price3;
        this.qua = qua;
    }

    public String getTitle3() {
        return title3;
    }

    public String getThumbnail2() {
        return thumbnail2;
    }

    public String getPrice3() {
        return price3;
    }

    public String getQua() {
        return qua;
    }
}
