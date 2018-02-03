package ca.six.archdemo.intro.transfer.auto2;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ca.six.archdemo.R;


public class TransformDemo3 extends AppCompatActivity {
    TransformViewModel3 vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);

        Button btn1 = findViewById(R.id.btn_simple);
        Button btn2 = findViewById(R.id.btn_simple2);

        vm = ViewModelProviders.of(this).get(TransformViewModel3.class);
        vm.getName().observe(this, name -> {
            btn1.setText("name->test || " + name);
        });
        vm.getPenName().observe(this, penName -> {
            btn2.setText("name->szw || " + penName);
        });
    }

    public void onClickSimpleButton(View v) {
        vm.updateName("test");
    }

    public void onClickSimpleButton2(View v) {
        vm.updateName("szw");
    }
}
