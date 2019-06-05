package com.thegloriousfountainministries.exp2.custom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by My HP on 11/22/2017.
 */

public class ChatClass {
    @SerializedName("message")
    public String message;
    @SerializedName("message_type")
    public String messageType;
    @SerializedName("message_time")
    public String messageTime;

    public ChatClass(String message, String messageType, String messageTime) {
        this.message = message;
        this.messageType = messageType;
        this.messageTime = messageTime;

    }

    public String getMessage() {
        return message;
    }

    public String getMessageType() {
        return messageType;
    }
        public String getMessageTime() {
        return messageTime;
    }



    public ChatClass() {

    }
}
