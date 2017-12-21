package ca.six.dep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ca.six.biz.BizVersion;
import ca.six.common.CommonVersion;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String v2 = new BizVersion().getFormatVersion();
//        int v1 = new CommonVersion().getVersion();
    }
}
