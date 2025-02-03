package com.example.notifications;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

public class NotificationHelper  extends MainActivity {

    private static final String CHANNEL_ID = "default channel";
    private static final String CHANNEL_NAME = "Kanał powiadomień";
    private static final int NOTIFICATION_ID = 1;

    public static void setNotification(AppCompatActivity activity, String title, String message){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }
        NotificationManager notificationManager = (NotificationManager)
                activity.getSystemService(activity.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            notificationManager.createNotificationChannel(channel);

        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(activity, CHANNEL_ID)
                        .setSmallIcon(R.drawable.dice2)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);



        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}