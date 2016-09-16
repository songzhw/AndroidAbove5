package ca.six.and7.split_screen;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import ca.six.and7.R;

public class SplitScreenActivity02 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("szw SplitScreenActivity02 onCreate()");
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((TextView)(findViewById(R.id.textView3))).setText("Split 002");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        System.out.println("szw SplitScreenActivity02 onNewIntent()");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("szw SplitScreenActivity02 onConfigurationChanged()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("szw SplitScreenActivity02 onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("szw SplitScreenActivity02 onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("szw SplitScreenActivity02 onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("szw SplitScreenActivity02 onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("szw SplitScreenActivity02 onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("szw SplitScreenActivity02 onDestroy()");
    }
}