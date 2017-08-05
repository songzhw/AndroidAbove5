package cn.six.sup.g_component.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;


public class Watcher01 implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {
        System.out.println("szw watcher : onAny(" + event.name() + ")");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        System.out.println("szw watcher : onResume()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        System.out.println("szw watcher : onPause()");
    }
}

/*
Observer方法可以接收0,1或2个参数。
如果使用，
    第一个参数必须是LifecycleOwner类型，
    第二个参数必须是Lifecycle.Event类型
*/
