package cn.six.sup.other.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.TextView;

import cn.six.sup.R;


public class Notification_A extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);

        TextView tv = (TextView) findViewById(R.id.tv_simple);
        tv.setText("Page AAA || "+getIntent().getStringExtra("key"));
    }

    public void onClickSimpleButton(View v){showNotification();}
    public void onClickSimpleButton2(View v){}

    private void showNotification(){
        Intent it1 = new Intent(this, Notification_B.class);
        it1.putExtra("key","This is B");
        PendingIntent pi1 = PendingIntent.getActivity(this, 0, it1, 0);

        Intent it2 = new Intent(this, Notification_C.class);
        it2.putExtra("key","This is C");
        PendingIntent pi2 = PendingIntent.getActivity(this, 0, it2, 0);

        Intent it3 = new Intent(this, Notification_D.class);
        it3.putExtra("key","This is D");
        PendingIntent pi3 = PendingIntent.getActivity(this, 0, it3, 0);

        Intent it4 = new Intent(this, Notification_E.class);
        it4.putExtra("key","This is E");
        PendingIntent pi4 = PendingIntent.getActivity(this, 0, it4, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Title")
                .setContentText("Good Friday is coming")
                .setSmallIcon(R.drawable.ic_pets)
                .setContentIntent(pi4)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_alarm, "Alarm", pi1)
                .addAction(R.drawable.ic_notifications, "Notify", pi2)
                .addAction(R.drawable.ic_radio_checked, "check", pi3)
                .build();
        NotificationManagerCompat compat = NotificationManagerCompat.from(this);
        compat.notify(23, notification);

    }

}
