package cn.six.sup.rv.viewholder;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.OnRvItemClickListener;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.OneAdapter;

public class ViewHolder_PositionActivity extends Activity {
    private RecyclerView rv;
    private OneAdapter adapter;

    private int state = 0;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_demo);

        rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OneAdapter<String>(R.layout.item_rv_cards) {
            @Override
            protected void apply(RvViewHolder vh, String value, int position) {
                vh.setText(R.id.tv_rv_card_item, value);
            }
        };
        data = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            data.add("Item : " + i);
        }
        adapter.data = data;
        rv.setAdapter(adapter);

        rv.addOnItemTouchListener(new OnRvItemClickListener(rv) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                System.out.println("szw layoutPos  = " + vh.getLayoutPosition());
                System.out.println("szw adapterPos = " + vh.getAdapterPosition());
                System.out.println("szw old_Pos    = " + vh.getOldPosition());
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
            }
        });
    }


    @Override
    public void onBackPressed() {

        if (state == 0) {
            data.add(0, "New 00");
            data.add(1, "New 01");
            adapter.notifyItemInserted(0);
            adapter.notifyItemInserted(1);
            rv.scrollToPosition(0);
            state = 1;
        } else if (state == 1) {
            data.clear();
            for (int i = 0; i < 28; i++) {
                data.add("-- pos = " + i);
            }
            adapter.notifyDataSetChanged();
            state = 0;
        } else {
            super.onBackPressed();
        }
    }
}