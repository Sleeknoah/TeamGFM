package com.thegloriousfountainministries.exp2.broadcast;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.thegloriousfountainministries.exp2.R;
import com.thegloriousfountainministries.exp2.pages.MainActivity;

/**
 * Created by My HP on 9/20/2017.
 */

public class PushService extends Service {
    MediaPlayer mediaPlayer;
    Context context;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        mediaPlayer =MediaPlayer.create(this, R.raw.despacito);
        mediaPlayer.start();

        Intent intent1 = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent pend = PendingIntent.getActivity(this, 0, intent1, 0);
        NotificationManager man = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_notification_overlay)
                        .setContentTitle(getResources().getString(R.string.app_name))
                        .setContentText("Get today's devotional now!!!")
                        .setContentIntent(pend);

        man.notify(0, mBuilder.build());
        Log.v("We are in service: ", "Yay!!!" + startId + ": " + intent);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
       mediaPlayer.stop();
    }
}
