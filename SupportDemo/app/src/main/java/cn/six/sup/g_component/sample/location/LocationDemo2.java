package cn.six.sup.g_component.sample.location;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.six.sup.g_component.LifeAppCompatActivity;


public class LocationDemo2 extends LifeAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocationWatcher watcher = new LocationWatcher(this);
        getLifecycle().addObserver(watcher);
    }

}
