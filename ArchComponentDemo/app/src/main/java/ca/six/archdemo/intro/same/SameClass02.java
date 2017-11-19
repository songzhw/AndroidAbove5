package ca.six.archdemo.intro.same;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import ca.six.archdemo.R;

/**
 * Created by songzhw on 2017/11/18.
 */

public class SameClass02 extends AppCompatActivity {
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
