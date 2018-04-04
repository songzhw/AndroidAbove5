package ca.six.archdemo;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
