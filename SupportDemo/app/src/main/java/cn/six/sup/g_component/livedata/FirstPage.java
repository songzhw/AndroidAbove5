package cn.six.sup.g_component.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.six.sup.R;

public class FirstPage extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);
        tv = findViewById(R.id.tv_simple);


        // 注意, 这是android.arch.lifecycle包里的Observer. 不是java.util包里的.
        final Observer<Worker> workerObserver = new Observer<Worker>() {
            @Override
            public void onChanged(@Nullable Worker worker) {
                tv.setText(worker == null ? "(empty)" : worker.toString());
            }
        };

        MutableLiveData<Worker> worker = StaticDataContainer.worker;
        worker.observe(this, workerObserver); //被废弃的LifeRegistryOwner, 是LifeOwner的子类. 所以第一参在这里没报错!
    }

    public void onClickSimpleButton(View v){
        startActivity(new Intent(this, SecondPage.class));
    }


    public void onClickSimpleButton2(View v){

    }
}
