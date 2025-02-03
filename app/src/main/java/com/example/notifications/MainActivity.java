package com.example.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.graphics.Bitmap;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "my_chanel_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        Button button = findViewById(R.id.Button);
        button.setOnClickListener(v->{
            NotificationHelper.setNotification(this,"Nowe powiadomienie","skibidi");
        });

        Button Buttonlong = findViewById(R.id.ButtonLong);
        Buttonlong.setOnClickListener(v->{
            sendNotificationLong();
        });
        Button ButtonPicture = findViewById(R.id.PictureButton);
        ButtonPicture.setOnClickListener(v->{
            sendNotificationPicture();
        });
    }
    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Kanał powiadomień";
            String description = "Opis kanału powiadomień";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
    private void sendNotification(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS},1);
                return;
            }
        }

        Intent intent = new Intent(MainActivity.this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.dice0)
                        .setContentTitle("Nowe Powiadomienie 3TP-E")
                        .setContentText("To jest tekst powiadomienia")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(1,builder.build());
    }


private void sendNotificationLong(){

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
        if(checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS},1);
            return;
        }


    Intent intent = new Intent(MainActivity.this, MainActivity.class);

    PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

    NotificationCompat.Builder builder =
            new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.dice2)
                    .setContentTitle("Długie Powiadomienie 3TP-e")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum vitae ligula nec aliquam. Suspendisse ac quam odio. Nunc feugiat turpis ut justo volutpat, in efficitur ante condimentum. Proin ante est, aliquam ac orci sit amet, scelerisque aliquam arcu. Integer quis purus nisi. Mauris sit amet accumsan nisi. Integer sed imperdiet mi, luctus gravida est. Praesent condimentum at nunc in posuere. Nam tempus nunc vitae quam pellentesque luctus. Fusce ultrices iaculis dapibus. Morbi nec ultricies nisl. Suspendisse est eros, semper nec velit eu, aliquam volutpat nunc. Fusce orci nulla, feugiat et augue vitae"))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
    NotificationManagerCompat notificationManager =
            NotificationManagerCompat.from(this);
    notificationManager.notify(1,builder.build());
        }
    }
    private void sendNotificationPicture(){

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dice0);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS},1);
                return;
            }


            Intent intent = new Intent(MainActivity.this, MainActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this, CHANNEL_ID)
                            .setSmallIcon(R.drawable.dice2)
                            .setContentTitle("Powiadomienie Obraz 3TPE")
                            .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);
            NotificationManagerCompat notificationManager =
                    NotificationManagerCompat.from(this);
            notificationManager.notify(1,builder.build());
        }
    }
}