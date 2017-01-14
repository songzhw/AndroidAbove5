package cn.six.sup.other.chrome;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;

import static android.support.customtabs.CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION;

/**
 * Created by songzhw on 2017-01-13
 */

public class ChromeJumperDemo extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btn = new Button(this);
        btn.setText("jump to my github");
        btn.setOnClickListener(this);
        setContentView(btn);

//        String pkg = getPackageNameToUse(this);
//        ChromeWarmUp warmUp = new ChromeWarmUp(this);
//        CustomTabsClient.bindCustomTabsService(this, pkg, warmUp);
    }

    @Override
    public void onClick(View v) {
        String url = "https://github.com/songzhw";
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

        builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_pets); // 图标的高度一般为24dp，宽度一般为24-48dp。
        PendingIntent pendingIntent = createPendingIntent(ActionBroadcastReceiver.ACTION_ACTION_BUTTON);
        builder.setActionButton(icon, "Pets", pendingIntent);

        builder.addMenuItem("toast 01", pendingIntent);
        builder.addMenuItem("toast 02", pendingIntent);

//        builder.setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left);
//        builder.setExitAnimations(this, R.anim.slide_in_left, R.anim.slide_out_right);

        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));  //加载url
    }

    private PendingIntent createPendingIntent(int actionSourceId) {
        Intent actionIntent = new Intent(
                this.getApplicationContext(), ActionBroadcastReceiver.class);
        actionIntent.putExtra(ActionBroadcastReceiver.KEY_ACTION_SOURCE, actionSourceId);
        return PendingIntent.getBroadcast(
                getApplicationContext(), actionSourceId, actionIntent, 0);
    }

}