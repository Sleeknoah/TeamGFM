package com.thegloriousfountainministries.exp2.custom;

/**
 * Created by My HP on 11/9/2017.
 */

public class DescClass {
    private String title;
    private String desc;
    private int backgroundColor;
    private String pics;

    public DescClass(String title, String desc, int backgroundColor, String pics) {
        this.title = title;
        this.desc = desc;
        this.backgroundColor = backgroundColor;
        this.pics = pics;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public String getPics() {
        return pics;
    }

    public DescClass() {

    }
}
