package ca.six.archdemo.intro.room.basic;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import ca.six.archdemo.R;


public class RoomBasicDemo extends Activity {

    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);
        tv = findViewById(R.id.tv_simple);

    }

    public void onClickSimpleButton(View v) {

    }

    public void onClickSimpleButton2(View v) {

    }

}
