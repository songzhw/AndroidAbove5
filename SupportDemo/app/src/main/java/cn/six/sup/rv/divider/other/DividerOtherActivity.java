package cn.six.sup.rv.divider.other;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.OneAdapter;

public class DividerOtherActivity extends AppCompatActivity {

    static final int OUT_SET_LEFT = 0;
    static final int OUT_SET_TOP = 0;
    static final int OUT_SET_RIGHT = 0;
    static final int OUT_SET_BOTTOM = 50;

    private RecyclerView rv;
    private OneAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_demo);

        rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        adapter = new OneAdapter<String>(R.layout.item_rv_cards) {
            @Override
            protected void apply(RvViewHolder vh, String value, int position) {
                vh.setText(R.id.tv_rv_card_item, value);
            }
        };
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            data.add("Item : " + i);
        }
        adapter.data = data;
        rv.setAdapter(adapter);
        rv.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        rv.addItemDecoration(new VerticalDividerDecoration());
    }
}