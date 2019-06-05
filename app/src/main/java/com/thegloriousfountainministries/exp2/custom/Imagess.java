package com.thegloriousfountainministries.exp2.custom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by My HP on 3/31/2017.
 */

public class Imagess {
    @SerializedName("p1")
    private String p1;

    @SerializedName("p2")
    private String p2;

    @SerializedName("p3")
    private String p3;

    @SerializedName("p4")
    private String p4;

    @SerializedName("p5")
    private String p5;

    @SerializedName("p6")
    private String p6;

    @SerializedName("v1")
    private String v1;

    @SerializedName("v2")
    private String v2;







    public Imagess() {

    }

    public Imagess(String p1, String p2, String p3, String p4, String p5, String p6, String v1, String v2) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
        this.v1 = v1;
        this.v2 = v2;
    }

    public String getP1() {
        return p1;
    }

    public String getP2() {
        return p2;
    }

    public String getP3() {
        return p3;
    }

    public String getP4() {
        return p4;
    }

    public String getP5() {
        return p5;
    }

    public String getP6() {
        return p6;
    }

    public String getV1() {
        return v1;
    }

    public String getV2() {
        return v2;
    }
}
