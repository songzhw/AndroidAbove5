package cn.six.sup.clay.nested_scroll;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;


public class NestedScrollSimpleDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_simple_demo);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rvNestedScrollBottom);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<String> data = new ArrayList<>();
        for(int i = 10; i< 50; i++){
            data.add("Item "+i);
        }

        OneAdapter<String> adapter = new OneAdapter<String>(R.layout.item_rv_one, data) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                vh.setText(R.id.tv_rv_item, s);
            }
        };
        rv.setAdapter(adapter);

    }
}
