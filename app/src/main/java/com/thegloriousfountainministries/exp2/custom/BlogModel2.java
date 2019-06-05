package com.thegloriousfountainministries.exp2.custom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by My HP on 7/19/2018.
 */

public class BlogModel2 {
    @SerializedName("blog_title")
    public String res1;
    @SerializedName("blog_link")
    public String res2;

    @SerializedName("blog_pics")
    public String res3;

    public String getRes1() {
        return res1;
    }

    public String getRes2() {
        return res2;
    }

    public String getRes3() {
        return res3;
    }
}
