package cn.six.sup.g_component.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.six.sup.R;

public class LifeWatcherDemo01 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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

/*
// initial launch
szw onCreate 01
szw watcher01 : onAny(ON_CREATE)
szw watcher01 : onAny(ON_START)
szw onResume 01
szw watcher01 : onAny(ON_RESUME)
szw watcher01 : onResume()

// press home key
szw watcher01 : onAny(ON_PAUSE)
szw watcher01 : onPause()
szw onPause 01
szw watcher01 : onAny(ON_STOP)

// bring it back
 szw watcher01 : onAny(ON_STOP)
 szw watcher01 : onAny(ON_START)
 szw onResume 01
 szw watcher01 : onAny(ON_RESUME)
 szw watcher01 : onResume()

 // exit
 szw watcher01 : onAny(ON_PAUSE)
szw watcher01 : onPause()
szw onPause 01
szw watcher01 : onAny(ON_STOP)
szw watcher01 : onAny(ON_DESTROY)
szw onDestroy 01
 */
