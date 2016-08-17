package cn.song.and5.snackbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import cn.song.and5.R;

// SnackBar is similar with Toast, which will disapear after several seconds

// requirement
// 1. AppCompatActivity, rather than Activity
// 2. @style/Theme.AppCompat (or its child)

public class SnackBarTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);
    }

    public void onClickSnack01(View view){
        Snackbar snackbar = Snackbar.make(view, "Snack Bar Text", Snackbar.LENGTH_LONG);
        snackbar.show();

//         snackbar.dismiss();
    }

    public void onClickSnack02(View view) {
        Snackbar.make(view, "Snack Bar Text", Snackbar.LENGTH_LONG)
                .setAction("Revert!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("szw", "click revert!");
                    }
                })
                .setActionTextColor(Color.RED)
                .show();
    }
}
