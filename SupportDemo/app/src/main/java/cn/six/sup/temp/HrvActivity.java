package cn.six.sup.temp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.OnRvItemClickListener;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.header.HeaderWrapper;
import cn.six.sup.rv.one_adapter.OneAdapter;

public class HrvActivity extends AppCompatActivity {
    private RecyclerView rv;
    private OneAdapter<String> adapter;
    private List<String> data;
    private HeaderWrapper headerWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrv);

        rv = (RecyclerView) findViewById(R.id.rv_data);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new OneAdapter<String>(R.layout.item_hrv) {
            @Override
            protected void apply(RvViewHolder vh, String value, int position) {

            }
        };
        adapter.data = data;

        LayoutInflater inflater = this.getLayoutInflater();
        View headView = inflater.inflate(R.layout.item_hrv, null); // TODO change head layout
        headerWrapper = new HeaderWrapper(adapter);
        headerWrapper.headerView = headView;
        rv.setAdapter(headerWrapper);


        rv.addOnItemTouchListener(new OnRvItemClickListener(rv) {
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
            }

            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {

                headerWrapper.notifyDataSetChanged();
            }
        });

        // ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RvItemDragSwipeCallback(this));
        // itemTouchHelper.attachToRecyclerView(rv);        
    }
}
