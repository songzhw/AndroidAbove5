package cn.six.sup.g_component.lifecycle.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.six.sup.g_component.LifeAppCompatActivity;


public class ViewCanFellDemo extends LifeAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyCustomView view = new MyCustomView(this);
        getLifecycle().addObserver(view);
        setContentView(view);
    }

}
