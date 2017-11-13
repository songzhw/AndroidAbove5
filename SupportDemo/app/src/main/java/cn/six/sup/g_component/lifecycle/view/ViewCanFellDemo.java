package cn.six.sup.g_component.lifecycle.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.six.sup.g_component.LifeAppCompatActivity;


public class ViewCanFellDemo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyCustomView view = new MyCustomView(this);
        getLifecycle().addObserver(view);
        setContentView(view);
    }

}
