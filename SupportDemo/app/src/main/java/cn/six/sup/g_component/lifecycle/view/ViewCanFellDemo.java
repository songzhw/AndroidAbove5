package cn.six.sup.g_component.lifecycle.view;

import android.app.Activity;
import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;


public class ViewCanFellDemo extends LifecycleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyCustomView view = new MyCustomView(this);
        setContentView(view);

        getLifecycle().addObserver(view);
    }
}
