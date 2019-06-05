package com.thegloriousfountainministries.exp2.custom;

import android.app.Application;

import co.paystack.android.PaystackSdk;

/**
 * Created by My HP on 12/19/2018.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        PaystackSdk.initialize(getApplicationContext());
    }
}
