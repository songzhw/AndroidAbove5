package cn.six.sup.g_component.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.six.sup.R;
import cn.six.sup.g_component.LifeAppCompatActivity;


public class LifeWatcherDemo02 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);

        Watcher01 watcher01 = new Watcher01();
        getLifecycle().addObserver(watcher01);

        TextView tv = (TextView) findViewById(R.id.tv_simple);
        tv.setText("Watcher Demo 002");
    }

    public void onClickSimpleButton(View v) {
        startActivity(new Intent(this, LifeWatcherDemo02.class));
    }

    public void onClickSimpleButton2(View v) {
    }
}

