package cn.six.sup.rv.snap;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.OneAdapter;

public class SnapHelperDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_divider_one);

        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            numbers.add("$ " + i);
        }

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvDivider01);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv.setHasFixedSize(true);

        OneAdapter<String> adapter = new OneAdapter<String>(R.layout.item_horizontal_tv) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                vh.setText(R.id.id_info, s);
                if (position % 2 == 0) {
                    vh.setBackground(R.id.id_info, R.color.pale_green);
                } else {
                    vh.setBackground(R.id.id_info, R.color.white);
                }
            }
        };
        adapter.data = numbers;
        rv.setAdapter(adapter);

        SnapHelper snapHelper = new LeftSnapHelper();
        snapHelper.attachToRecyclerView(rv);
    }


}