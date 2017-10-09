package cn.six.sup.other.chrome;

import android.content.ComponentName;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;

import java.lang.ref.WeakReference;

/**
 * Created by songzhw on 2017-01-14
 */

public class ChromeWarmUp extends CustomTabsServiceConnection {
    private WeakReference<ChromeWarmUpCallback> callbackRef;

    public ChromeWarmUp(ChromeWarmUpCallback callback) {
        callbackRef = new WeakReference<ChromeWarmUpCallback>(callback);
    }

    @Override
    public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
        ChromeWarmUpCallback callback = callbackRef.get();
        if (callback != null) {
            callback.onServiceConnected(client);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        ChromeWarmUpCallback callback = callbackRef.get();
        if (callback != null) {
            callback.onServiceDisconnected();
        }
    }
}
