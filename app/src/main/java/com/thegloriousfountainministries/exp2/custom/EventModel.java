package com.thegloriousfountainministries.exp2.custom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by My HP on 3/31/2017.
 */

public class EventModel {
    @SerializedName("event_title")
    private String title;
    @SerializedName("event_date")
    private String date;

    @SerializedName("event_time")
    private String time;

    @SerializedName("event_going")
    private String going;

    @SerializedName("event_pic")
    private String event_pic;







    public EventModel() {

    }

    public EventModel(String title, String date, String time, String going, String event_pic) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.going = going;
        this.event_pic = event_pic;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getGoing() {
        return going;
    }

    public String getEvent_pic() {
        return event_pic;
    }
}
