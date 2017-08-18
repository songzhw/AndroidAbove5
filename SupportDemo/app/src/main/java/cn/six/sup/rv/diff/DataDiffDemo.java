package cn.six.sup.rv.diff;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;

public class DataDiffDemo extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_single_rv);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rvSingle);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("String Index " + i);
        }

        OneAdapter<String> adapter = new OneAdapter<String>(R.layout.item_single_rv, data) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                vh.setText(R.id.tvSingleRvItem, "xx => " + s);
            }
        };
        rv.setAdapter(adapter);
    }
}
