package com.thegloriousfountainministries.exp2;

import android.content.Context;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.thegloriousfountainministries.exp2.custom.Preference;

/**
 * Created by My HP on 6/19/2018.
 */

public class FirebaseInstance extends FirebaseInstanceIdService {
    private static final String TAGS = "Token";
    Preference preference;

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAGS, "Refreshed token: " + refreshedToken);
        preference = new Preference(this);
        preference.saveDetails(refreshedToken);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(refreshedToken);
    }
}
