package ca.six.archdemo.intro.same;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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

public class SameClass01 extends AppCompatActivity {
    private TextView tv;
    private ZeroViewModel vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);
        tv = findViewById(R.id.tv_simple);

        vm = ViewModelProviders.of(this).get(ZeroViewModel.class);
        System.out.println("szw SameClass01 : vm = "+vm);
        System.out.println("szw SameClass01 : " + vm.user);

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("szw SameClass01 onResume() "+vm.user);
    }

    // 启动同一类的第2个实例
    public void onClickSimpleButton(View v) {
        vm.user = new User(100, "SuperMario");
        startActivity(new Intent(this, SameClass01.class));
    }

    // 启动另一个类. 但启动后关了, 再启动. 看下vm的值.
    public void onClickSimpleButton2(View v) {
        startActivity(new Intent(this, SameClass02.class));
    }
}
