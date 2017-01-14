package cn.six.sup.other.chrome;

import android.support.customtabs.CustomTabsClient;

/**
 * Created by songzhw on 2017-01-14
 */

public interface ChromeWarmUpCallback {

    void onServiceConnected(CustomTabsClient client);

    void onServiceDisconnected();

}
