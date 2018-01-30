package ca.six.archdemo.how.lifecycle;

import android.os.Bundle;

public interface MyLifecycleListener {
    void onCreate();
    void onResume();
    void onPause();
    void onDestroy();
}
