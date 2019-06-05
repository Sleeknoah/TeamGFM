package com.thegloriousfountainministries.exp2.custom;

import android.support.annotation.Keep;

/**
 * Created by My HP on 12/22/2017.
 */

public class ImageUploader {
    @Keep
    public String name;
    public String url;
    public String filter;
    public String text;

    public ImageUploader(String name, String url, String filter, String text) {
        this.name = name;
        this.url = url;
        this.filter = filter;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getFilter() {
        return filter;
    }

    public String getText() {
        return text;
    }

    public ImageUploader() {
    }
}
