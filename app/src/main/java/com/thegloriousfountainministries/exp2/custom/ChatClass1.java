package com.thegloriousfountainministries.exp2.custom;

/**
 * Created by My HP on 11/22/2017.
 */

public class ChatClass1 {
    private String name;
    private String first_name;
    private String message2;
    private String number;
    private String time;
    private String timePeriod;
    private long messageTime;
//    private String message2;
//    private String pictures;
//    private boolean side;


    public ChatClass1(String name, String first_name, String message2, String number, String time, String timePeriod) {
        this.name = name;
        this.first_name = first_name;
        this.message2 = message2;
        this.number = number;
        this.time = time;
        this.timePeriod = timePeriod;
    }

    public String getName() {
        return name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getMessage2() {
        return message2;
    }

    public String getNumber() {
        return number;
    }

    public String getTime() {
        return time;
    }

    public String getTimePeriod() {
        return timePeriod;
    }
    //    public long getMessageTime() {
//        return messageTime;
//    }

    //    public String getMessage2() {
//        return message2;
//    }
//
//    public String getPictures() {
//        return pictures;
//    }

//    public boolean isSide() {
//        return side;
//    }

    public ChatClass1(String message, CharSequence format) {

    }
}
