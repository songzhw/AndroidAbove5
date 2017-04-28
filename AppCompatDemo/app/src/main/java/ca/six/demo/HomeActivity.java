package ca.six.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Title2");
        toolbar.setSubtitle("subtitle");
        toolbar.setLogo(R.drawable.ic_launcher_round);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left);
    }
}
