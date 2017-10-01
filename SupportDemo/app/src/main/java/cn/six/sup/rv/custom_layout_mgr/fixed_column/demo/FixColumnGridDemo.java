package cn.six.sup.rv.custom_layout_mgr.fixed_column.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import cn.six.sup.R;

public class FixColumnGridDemo extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_demo);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(this, 6));

        GridDemoAdapter adapter = new GridDemoAdapter();

        rv.setAdapter(adapter);
    }


}
