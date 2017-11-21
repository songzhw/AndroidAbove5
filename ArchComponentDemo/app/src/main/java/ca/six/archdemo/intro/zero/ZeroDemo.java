package ca.six.archdemo.intro.zero;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import ca.six.archdemo.R;
import ca.six.archdemo.data.User;

public class ZeroDemo extends AppCompatActivity {
    private TextView tv;
    private ZeroViewModel vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);
        tv = findViewById(R.id.tv_simple);

        String value = savedInstanceState == null ? "emptyBundle" : savedInstanceState.getString("key");
        System.out.println("szw onCreate() " + value);

        vm = ViewModelProviders.of(this).get(ZeroViewModel.class);
        System.out.println("szw vm.user = " + vm.user);

        System.out.println("szw other = "+SameVm.user);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("szw onDestroy()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String value = outState == null ? "emptyBundle" : outState.getString("key");
        System.out.println("szw onSaveInstanceState() " + value);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String value = savedInstanceState == null ? "emptyBundle" : savedInstanceState.getString("key");
        System.out.println("szw onRestoreInstanceState() " + value);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("szw onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("szw onPause()");
    }

    public void onClickSimpleButton(View v) {
        vm.user = new User(23, "jorden");
        SameVm.user = new User(21, "king");
    }


    public void onClickSimpleButton2(View v) {
    }
}
