package com.thegloriousfountainministries.exp2.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by My HP on 9/20/2017.
 */

public class AlarmReciever extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent(context, RingtoneService.class);
        context.startService(intent2);
        Log.v("We are in: ", "Yay");
    }
}
