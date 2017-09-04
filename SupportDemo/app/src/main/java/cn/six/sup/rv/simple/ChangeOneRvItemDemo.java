package cn.six.sup.rv.simple;

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

public class ChangeOneRvItemDemo extends Activity implements View.OnClickListener {

    private OneAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_single_rv);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rvSingle);
        rv.setLayoutManager(new LinearLayoutManager(this));

        final List<String> data = new ArrayList<>();
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
        rv.addOnItemTouchListener(new OnRvItemClickListener(rv) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int position = vh.getAdapterPosition();
                data.set(position, "I'm different "+position);
                adapter.notifyItemChanged(position);
                System.out.println("szw click2 rv item : "+position);
            }
        });



        findViewById(R.id.btnSingleRv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }
}
