package ca.six.archdemo.intro.zero;


import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import ca.six.archdemo.R;
import ca.six.archdemo.pojo.User;

public class ZeroDemo extends AppCompatActivity {
    private TextView tv;
    private ZeroViewModel vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);
        tv = findViewById(R.id.tv_simple);

        String value = savedInstanceState == null ? "emptyBundle" : savedInstanceState.getString("key");
        System.out.println("szw onCreate() bundle's value = " + value);

        vm = ViewModelProviders.of(this).get(ZeroViewModel.class);
        System.out.println("szw onCreate() vm.user = " + vm.user);

        System.out.println("szw onCreate() other = "+SameVm.user);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("szw onDestroy() isConfigChange = "+isChangingConfigurations() + " ;" +
                "isFinish = "+isFinishing());
        // 正常退出时, 会有isConfigChanging = false; isFinish = true
        // 转屏时, isConfigChanging = true; isFinish = false
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key", "valueInOnSaveInstanceState");
        System.out.println("szw onSaveInstanceState() saved a value" );
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String value = savedInstanceState == null ? "emptyBundle" : savedInstanceState.getString("key");
        System.out.println("szw onRestoreInstanceState() " + value);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("szw onConfigurationChanged()");

    }

    @Override
    protected void onResume() {
        super.onResume();
        vm = ViewModelProviders.of(this).get(ZeroViewModel.class);
        System.out.println("szw onResume() vm.user = " + vm.user);
        System.out.println("szw onResume() other = "+SameVm.user);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("szw onPause()");
    }

    public void onClickSimpleButton(View v) {
        vm.user = new User(23, "jorden");
        SameVm.user = new User(21, "king");
        System.out.println("szw saved values in vm and static");
    }


    public void onClickSimpleButton2(View v) {
    }
}
