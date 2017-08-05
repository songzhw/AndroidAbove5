package cn.six.sup.g_component.lifecycle;

import android.arch.lifecycle.LifecycleActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.six.sup.R;


public class LifeWatcherDemo01 extends LifecycleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);

        Watcher01 watcher01 = new Watcher01();
        getLifecycle().addObserver(watcher01);
    }

    public void onClickSimpleButton(View v) {
        startActivity(new Intent(this, LifeWatcherDemo01.class));
    }

    public void onClickSimpleButton2(View v) {
    }
}

