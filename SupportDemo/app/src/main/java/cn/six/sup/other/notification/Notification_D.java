package cn.six.sup.other.notification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import cn.six.sup.R;


public class Notification_D extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);

        TextView tv = (TextView) findViewById(R.id.tv_simple);
        tv.setText("Page DDD || "+getIntent().getStringExtra("key"));
    }

    public void onClickSimpleButton(View v){}
    public void onClickSimpleButton2(View v){}

}
