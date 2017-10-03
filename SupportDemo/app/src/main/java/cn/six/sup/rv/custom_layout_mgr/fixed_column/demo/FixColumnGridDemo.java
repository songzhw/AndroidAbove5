package cn.six.sup.rv.custom_layout_mgr.fixed_column.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.custom_layout_mgr.fixed_column.demo.entity.Action;
import cn.six.sup.rv.custom_layout_mgr.fixed_column.demo.entity.IFixedGridType;
import cn.six.sup.rv.custom_layout_mgr.fixed_column.demo.entity.Name;
import cn.six.sup.rv.custom_layout_mgr.fixed_column.demo.entity.Numbera;

public class FixColumnGridDemo extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_demo);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(this, 6));

        List<IFixedGridType> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Name name = new Name();
            name.name = "Item " + i;
            name.subName = "description " + i;
            data.add(name);

            for (int j = 0; j < 20; j++) {
                Numbera num = new Numbera();
                num.num = i * 30 + j;
                data.add(num);
            }

            Action action = new Action();
            data.add(action);
        }
        GridDemoAdapter adapter = new GridDemoAdapter();
        adapter.data = data;
        rv.setAdapter(adapter);

    }


}
