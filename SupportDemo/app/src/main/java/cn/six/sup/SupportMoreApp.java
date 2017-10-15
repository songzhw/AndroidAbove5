package cn.six.sup;

import android.app.Application;

import com.github.moduth.blockcanary.BlockCanary;


public class SupportMoreApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
    }
}
