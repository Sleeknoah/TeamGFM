package com.thegloriousfountainministries.exp2;

import android.app.Notification;
import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by My HP on 7/6/2018.
 */

public class FirebaseMessaging extends FirebaseMessagingService {
    String message, title, noti;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        title= remoteMessage.getData().get("title");
        message = remoteMessage.getData().get("message");


        Log.d("MESSAGE", "Message body:" + message);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);



        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.gflogoss)
                .setContentTitle(title)
                .setContentText(message)
                .setSound(defaultSoundUri)
                .setPriority(Notification.PRIORITY_HIGH)
                .setColor(getResources().getColor(R.color.colorPrimary));
        notificationManager.notify(1, builder.build());
    }
}
