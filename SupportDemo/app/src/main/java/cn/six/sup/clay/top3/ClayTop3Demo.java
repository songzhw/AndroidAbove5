package cn.six.sup.clay.top3;

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
 * Created by songzhw on 2016-12-27
 */

public class ClayTop3Demo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clay_top_three);

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
    }
}
