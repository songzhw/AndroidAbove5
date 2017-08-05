package cn.six.sup.g_component.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

public class Watcher01 implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(){
        System.out.println("szw watcher : onAny()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){
        System.out.println("szw watcher : onResume()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){
        System.out.println("szw watcher : onPause()");
    }
}
