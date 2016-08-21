package cn.six.sup.rv.waterfall;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.six.sup.R;

/**
 * @author hzxuxiaolin
 * @date 2015/7/8
 * Copyright 2015 Six. All rights reserved.
 */

public class RvWaterFallActivity extends Activity {
    private RecyclerView rv;
    private List<String> data;
    private RvWaterFallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initData();
        initView();
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++) {
            data.add("" + (char) i);
        }
    }

    private void initView() {
        final ActionBar ab = getActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_launcher);
        ab.setDisplayHomeAsUpEnabled(true);

        adapter = new RvWaterFallAdapter();
        rv = (RecyclerView) findViewById(R.id.rvRefresh);
//        rv.setLayoutManager(new LinearLayoutManager(this));
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
//        rv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
//        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
//        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(adapter);
        rv.addItemDecoration(new GridDividerDecoration(this));
        rv.setItemAnimator(new DefaultItemAnimator());

    }


    private class RvWaterFallAdapter extends RecyclerView.Adapter<RvWaterFallAdapter.WaterfallHolder> {
        private int[] heights = new int[data.size()];

        public RvWaterFallAdapter() {
            Random rd = new Random();
            for (int i = 0; i < data.size(); i++) {
                heights[i] = rd.nextInt(300) + 100;
            }
        }

        @Override
        public WaterfallHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new WaterfallHolder(LayoutInflater.from(RvWaterFallActivity.this).inflate(R.layout.item_rv, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(WaterfallHolder rvHolder, int i) {
            rvHolder.tvItem.setText(data.get(i));
            rvHolder.tvItem.setHeight(heights[i]);
        }

        @Override
        public int getItemCount() {
            return data != null ? data.size() : 0;
        }

        public void addData(int position) {
            data.add(position, "item " + position);
            notifyItemInserted(position);
        }

        public void removeData(int position) {
            data.remove(position);
            notifyItemRemoved(position);
        }

        public class WaterfallHolder extends RecyclerView.ViewHolder {
            TextView tvItem;

            public WaterfallHolder(View itemView) {
                super(itemView);
                tvItem = (TextView) itemView.findViewById(R.id.tv_rv_item);
            }
        }
    }
}
