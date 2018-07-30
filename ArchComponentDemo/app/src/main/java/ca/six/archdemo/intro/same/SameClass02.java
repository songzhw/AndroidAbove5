package ca.six.archdemo.intro.same;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import ca.six.archdemo.R;
import ca.six.archdemo.pojo.User;
import ca.six.archdemo.intro.zero.ZeroViewModel;

/**
 * Created by songzhw on 2017/11/18.
 */

public class SameClass02 extends AppCompatActivity {
    private TextView tv;
    private ZeroViewModel vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);
        tv = findViewById(R.id.tv_simple);
        vm = ViewModelProviders.of(this).get(ZeroViewModel.class);
        System.out.println("szw SameClass02 onCreate() : " + vm.user);
    }

    public void onClickSimpleButton(View v) {
        vm.user = new User(22, "test");
    }


    public void onClickSimpleButton2(View v) {
        System.out.println("szw SameClass02 : saved = "+vm.user);
    }
}
