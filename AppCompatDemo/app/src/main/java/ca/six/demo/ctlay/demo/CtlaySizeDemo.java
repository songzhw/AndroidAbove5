package ca.six.demo.ctlay.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ca.six.demo.R;

// ctlay中的尺寸问题
public class CtlaySizeDemo extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctlay_ratio);
    }
}
