/*
 * Copyright 2016-2017 Tom Misawa, riversun.org@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 *  INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR
 * IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package com.ananta.myapplication.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.ananta.myapplication.R;
import com.ananta.myapplication.customer.SingleNotificationActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import util.AlaBricks;

/**
 * Service called when firebase cloud message received
 *
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    String val4="";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Logg.d();


        final Map<String, String> data = remoteMessage.getData();
        final String val1 = data.get("type");
        final String val2 = data.get("title");
        final String val3 = data.get("description");
        val4 = data.get("image");
        Log.w("Received",""+val1+" - "+val2+" - "+val3+" - "+val4);
        Logg.d("Message received val1=" + val1 + " val2=" + val2);
//       Log.w("IMAGENAME",val4);
        Handler handler = new Handler(getMainLooper());
        handler.post(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(MyFirebaseMessagingService.this.getApplicationContext(),
                        "Message received " + "val1=" + val1 + " val2=" + val2, Toast.LENGTH_SHORT).show();
            }
        });

        Bitmap bitmap = null;
        if(val4==null)
        {
            val4="";
        }
        if(val4.length()>0) {
          bitmap = getBitmapfromUrl(val4);
        }
        else
        {
            bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.logo);
        }
        showNotification(val2,val3,val1,bitmap);
    }

    @Override
    public void onNewToken(String registrationToken) {

        Logg.d("Firebase #onNewToken registrationToken=" + registrationToken);
        startService(new Intent(getApplicationContext(), FcmTokenRegistrationService.class));
    }

    void showNotification(String title, String message, String type, Bitmap bitmap) {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("prakash",
                    "newPrakash",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "YOUR_CHANNEL_ID")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle(title)
                .setChannelId("prakash")
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))// title for notification
                .setContentText(message)// message for notification
                .setAutoCancel(true); // clear notification after click
        Intent intent = null;
        AlaBricks.fromNotify ="1";
        if(type.equals("1"))
        {
            intent = new Intent(getApplicationContext(), SingleNotificationActivity.class);
        }
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }
    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }
}
