package cn.six.sup.g_component.livedata;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import cn.six.sup.R;

public class SecondPage extends Activity {
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);
        tv = (TextView) findViewById(R.id.tv_simple);
    }

    public void onClickSimpleButton(View v) {

    }


    public void onClickSimpleButton2(View v) {

    }
}
