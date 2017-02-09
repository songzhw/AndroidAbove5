package ca.six.and7.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.widget.Toast;

import ca.six.and7.R;

public class ReplyBroadcastReceiver extends BroadcastReceiver {
    private static final String KEY_NOTIFICATION_ID = "key_notify_id";

    @Override
    public void onReceive(Context context, Intent it) {
        if (And7NotificationDemo.ACTION_REPLY.equalsIgnoreCase(it.getAction())){
            CharSequence cs = getReplyMessage(it);
            Toast.makeText(context, "Message = "+cs, Toast.LENGTH_LONG).show();

            int notificationId = it.getIntExtra(KEY_NOTIFICATION_ID, 0);
            updateNotification(context, notificationId);
        }
    }

    private CharSequence getReplyMessage(Intent it){
        Bundle remoteInputBundle = RemoteInput.getResultsFromIntent(it);
        if(remoteInputBundle != null){
            return remoteInputBundle.getCharSequence(And7NotificationDemo.KEY_REPLY);
        }
        return null;
    }

    public static Intent getReplyIntent(int notificationId) {
        Intent it = new Intent(And7NotificationDemo.ACTION_REPLY);
        it.putExtra(KEY_NOTIFICATION_ID, notificationId); // 后面更新要用
        return it;
    }


    private void updateNotification(Context context, int notificationId) {
        NotificationManagerCompat mgr = NotificationManagerCompat.from(context);
        mgr.cancel(notificationId);
    }
}