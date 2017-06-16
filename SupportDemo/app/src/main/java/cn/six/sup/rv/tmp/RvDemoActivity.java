package cn.six.sup.rv.tmp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.OnRvItemClickListener;
import cn.six.sup.rv.one_adapter.OneAdapter;

import cn.six.sup.R;
import cn.six.sup.rv.tmp.data.TempData;

public class RvDemoActivity extends AppCompatActivity {
    private List<TempData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_demo);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        OneAdapter<TempData> adapter = new OneAdapter<TempData>(R.layout.item_rv_demo) {
            @Override
            protected void apply(RvViewHolder vh, TempData value, int position) {

            }
        };
        adapter.data = data;
        rv.setAdapter(adapter);

        rv.addOnItemTouchListener(new OnRvItemClickListener(rv) {
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
            }

            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {

            }
        });

        // ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RvItemDragSwipeCallback(this));
        // itemTouchHelper.attachToRecyclerView(rv);        
    }
}
