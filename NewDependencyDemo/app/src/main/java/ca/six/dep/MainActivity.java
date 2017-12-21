package ca.six.dep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ca.six.biz.CustomVersion;
import ca.six.common.VersionHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String v2 = new CustomVersion().getFormatVersion();
        int v1 = new VersionHelper().getVersion();
    }
}
