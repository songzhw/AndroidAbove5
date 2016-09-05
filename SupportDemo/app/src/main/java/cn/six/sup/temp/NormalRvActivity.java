package cn.six.sup.temp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;

import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.OnRvItemClickListener;
import cn.six.sup.rv.one_adapter.OneAdapter;
import cn.six.sup.rv.header.HeaderWrapper;

import cn.six.sup.R;

public class NormalRvActivity extends AppCompatActivity {
    private RecyclerView rv;
    private OneAdapter<String> adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_rv);

        rv = (RecyclerView) findViewById(R.id.rv_data);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new OneAdapter<String>(R.layout.item_normal_rv) {
            @Override
            protected void apply(RvViewHolder vh, String value, int position) {

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

                adapter.notifyDataSetChanged();
            }
        });

        // ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RvItemTouchHelperCallback(this));
        // itemTouchHelper.attachToRecyclerView(rv);        
    }
}
