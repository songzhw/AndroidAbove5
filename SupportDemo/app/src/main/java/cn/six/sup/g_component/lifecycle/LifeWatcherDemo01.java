package cn.six.sup.g_component.lifecycle;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.six.sup.R;

public class LifeWatcherDemo01 extends LifecycleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("szw onCreate 01");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);

        Watcher01 watcher01 = new Watcher01();
        getLifecycle().addObserver(watcher01);
        System.out.println("szw onCreate 01");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("szw onResume 01");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("szw onPause 01");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("szw onDestroy 01");
    }

    public void onClickSimpleButton(View v) {
    }

    public void onClickSimpleButton2(View v) {
    }
}


