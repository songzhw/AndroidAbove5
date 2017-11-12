package cn.six.sup.rv.others;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.OnRvItemClickListener;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;


public class RvCheckBoxDemo2 extends Activity implements View.OnClickListener {
    private OneAdapter<String> adapter;
    public static boolean isShowCheckBox = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_rv_cb_focus);

                    RecyclerView rv = (RecyclerView) findViewById(R.id.rvSingle);
                    rv.setLayoutManager(new LinearLayoutManager(this));

                    List<String> data = new ArrayList<>();
                    for (int i = 0; i < 30; i++) {
                        data.add("String Index " + i);
                    }

                    adapter = new OneAdapter<String>(R.layout.item_checkbox_tv, data) {
                        @Override
                        protected void apply(RvViewHolder vh, String s, int position) {
                            if (isShowCheckBox) {
                                vh.setVisibility(R.id.cb_cb_tv, View.VISIBLE);
                            } else {
                                vh.setVisibility(R.id.cb_cb_tv, View.GONE);
                }
                vh.setText(R.id.tv_cb_tv, "xx => " + s);
            }
        };
        rv.setAdapter(adapter);

        findViewById(R.id.btnSingleRv).setOnClickListener(this);

        rv.addOnItemTouchListener(new OnRvItemClickListener(rv) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                System.out.println("szw click + " + vh.getAdapterPosition());
            }
        });

        rv.setNestedScrollingEnabled(false);
    }

    @Override
    public void onClick(View v) {
        isShowCheckBox = !isShowCheckBox;
        adapter.notifyDataSetChanged();
    }
}
