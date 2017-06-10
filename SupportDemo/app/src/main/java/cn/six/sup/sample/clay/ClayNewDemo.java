package cn.six.sup.sample.clay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import cn.six.sup.R;

public class ClayNewDemo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ext_appbar_layout);

        appbar = (AppBarLayout)findViewById(R.id.extlay_home);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appbar.getLayoutParams();
        params.setBehavior(new AppBarLayout.Behavior());

        toolbar = (Toolbar)findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        setTitle("Title");


    }

    private AppBarLayout appbar;
    private Toolbar toolbar;
}