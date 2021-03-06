package cn.six.sup.clay.fixedtop;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.OneAdapter;

/**
 * Created by songzhw on 2016-12-25
 */

public class FixedTopDemo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clay_fixed_top_behavior);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvFixedTop);
        rv.setLayoutManager(new LinearLayoutManager(this));
        OneAdapter adapter = new OneAdapter<String>(R.layout.item_rv_cards) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                vh.setText(R.id.tv_rv_card_item, "item : ( " + s + " )");
            }
        };
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            data.add("" + i);
        }
        adapter.data = data;
        rv.setAdapter(adapter);

        // 加了这一句， 那整个FixedTopBehavior都会失效了！
//        rv.setNestedScrollingEnabled(false);
    }
}
