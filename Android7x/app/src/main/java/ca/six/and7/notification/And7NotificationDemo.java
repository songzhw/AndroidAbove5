package ca.six.and7.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
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





    public static final String KEY_REPLY = "KEY_REPLY";
    public static final String ACTION_REPLY = "ACTION_REPLY";

    public void click02(View v){
        int id = 222;

        RemoteInput replyRemoteInput = new RemoteInput.Builder(KEY_REPLY)
                .setLabel("input here to reply") // hint
                .build();
        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
                R.drawable.ic_reply, "reply", getReplyPendingIntent(this, id))
                .addRemoteInput(replyRemoteInput)
                .setAllowGeneratedReplies(true)
                . build();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_pets)
                .setContentTitle("Notification here")
                .setContentText("Second news. Hello World")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
                .setColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setGroup(notificationGroupID)
                .setGroupSummary(true)
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(), 0))
                .addAction(replyAction)
                .setAutoCancel(true);

        NotificationManagerCompat mgr = NotificationManagerCompat.from(this);
        mgr.notify(id, builder.build());
    }

    private PendingIntent getReplyPendingIntent(Context ctx, int notificationId ){
        Intent it = ReplyBroadcastReceiver.getReplyIntent( notificationId);
        return PendingIntent.getBroadcast(ctx, 100, it, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}