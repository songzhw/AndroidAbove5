package ca.six.archdemo.intro.transfer.auto2;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ca.six.archdemo.R;


public class TransformDemo3 extends AppCompatActivity {
    private TransformViewModel3 vm;
    private Button btn1, btn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);

        btn1 = findViewById(R.id.btn_simple);
        btn2 = findViewById(R.id.btn_simple2);

        vm = ViewModelProviders.of(this).get(TransformViewModel3.class);
        vm.getName().observe(this, name -> {
            String text = "name->test || " + name;
            System.out.println("szw activity get name = " + text);
            btn1.setText(text);
        });
        vm.getPenName().observe(this, penName -> {
            String text = "name->szw || " + penName;
            System.out.println("szw activity get penName = " + text);
            btn2.setText(text);
        });
    }

    public void onClickSimpleButton(View v) {
        vm.updateName("test");
    }

    public void onClickSimpleButton2(View v) {
        vm.updateName("szw");
    }
}
