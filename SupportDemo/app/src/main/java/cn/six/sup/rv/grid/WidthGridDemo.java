package cn.six.sup.rv.grid;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;

public class WidthGridDemo extends Activity {

    private RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_demo_wrap);

        final List<IWidthType> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(new WidthNumber("" + (100 + i)));
            data.add(new WidthNumber("" + (200 + i)));
            data.add(new WidthNumber("" + (300 + i)));
            data.add(new WidthAction());
        }

        rv = (RecyclerView) findViewById(R.id.rvRefresh);
        GridLayoutManager layMgr = new GridLayoutManager(this, 14);
        layMgr.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                IWidthType type = data.get(position);
                return type.teyp() == IWidthType.TYPE_NUM ? 3 : 5;
            }// num列是60dp, action列是100dp. 所以是3:5的比例
        });


        WidthGridAdapter adapter = new WidthGridAdapter();
        adapter.data = data;

        rv.setAdapter(adapter);
        rv.setLayoutManager(layMgr);
    }
}
