package ca.six.and7.split_screen;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ca.six.and7.R;

/**
 * Created by songzhw on 2016-09-15
 */

public class SplitScreenActivity01 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("szw SplitScreenActivity01 onCreate()");
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", (v) -> startActivity(new Intent(SplitScreenActivity01.this, SplitScreenActivity02.class)))
                    .show();
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        System.out.println("szw SplitScreenActivity01 onNewIntent()");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("szw SplitScreenActivity01 onConfigurationChanged() : "+newConfig.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("szw SplitScreenActivity01 onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("szw SplitScreenActivity01 onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("szw SplitScreenActivity01 onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("szw SplitScreenActivity01 onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("szw SplitScreenActivity01 onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("szw SplitScreenActivity01 onDestroy()");
    }
}

