package ca.six.and7.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import ca.six.and7.R;


public class And7NotificationDemo extends Activity {
    private String notificationGroupID = "GroupA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
    }

    public void click01(View v){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ic_launcher)
            .setContentTitle("title")
            .setContentText("This is a breaking news! Please see here")
            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_pets))
            .setColor(ContextCompat.getColor(this, R.color.colorAccent))
            .setGroupSummary(true)
            .setGroup(notificationGroupID)
            .setAutoCancel(true);

        int id = 1;
        NotificationManager mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mgr.notify(id, builder.build());
    }

    public void click02(View v){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ic_pets)
            .setContentTitle("Notification here")
            .setContentText("Second news. Hello World")
            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
            .setColor(ContextCompat.getColor(this, R.color.colorAccent))
            .setGroupSummary(true)
            .setGroup(notificationGroupID)
            .setAutoCancel(true);

        int id = 2;
        NotificationManager mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mgr.notify(id, builder.build());
    }
}