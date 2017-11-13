package cn.six.sup.rv.category;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;


public class CategoryDemo extends Activity {
    private RecyclerView rvLeft, rvRight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        rvLeft = findViewById(R.id.rvCategoryLeft);
        rvRight = findViewById(R.id.rvCategoryRight);

        List<String> leftData = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            leftData.add("Category " + i);
        }

        List<String> rightData = new ArrayList<>();
        for (int i = 0; i < 72; i++) {
            rightData.add("Item " + (i / 6) + " - " + (i % 6));
        }

        OneAdapter<String> leftAdapter = new OneAdapter<String>(R.layout.item_only_big_tv, leftData) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                vh.setText(R.id.tvInner,  s);
            }
        };
        OneAdapter<String> rightAdapter = new OneAdapter<String>(R.layout.item_tv_iv, rightData) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                vh.setSrc(R.id.iv_tviv, R.drawable.ic_launcher);
                vh.setText(R.id.tv_tviv, s);
            }
        };

        rvLeft.setLayoutManager(new LinearLayoutManager(this));
        rvRight.setLayoutManager(new GridLayoutManager(this, 4));
        rvLeft.setAdapter(leftAdapter);
        rvRight.setAdapter(rightAdapter);


    }
}
