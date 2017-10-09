package cn.six.sup.rv.diff;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;

public class DataDiffDemo extends Activity implements View.OnClickListener {

    private OneAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_single_rv);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvSingle);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("String Index " + i);
        }

        adapter = new OneAdapter<String>(R.layout.item_single_rv, data) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                vh.setText(R.id.tvSingleRvItem, "xx => " + s);
            }
        };
        rv.setAdapter(adapter);

        findViewById(R.id.btnSingleRv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        List<String> olds = adapter.data;

        int size = (int) (Math.random() * 50);
        List<String> news = new ArrayList<>();
        for (int i = 0; i < size; i++) {
//            news.add("(" + size + ") new size " + i);
            news.add("String Index " + i);
        }

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DemoDataDiff(olds, news));
        diffResult.dispatchUpdatesTo(adapter);
    }
}
