package cn.six.sup.rv.swipe_menu.rv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.OnRvItemClickListener;

public class SwipeMenuRvDemo extends AppCompatActivity {
    private RecyclerView rv;
    //    private OneAdapter<String> adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_menu_demo);

        rv = (RecyclerView) findViewById(R.id.rv_data);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

//        OneAdapter adapter = new OneAdapter<String>(R.layout.item_swipe_menu_demo) {
//            @Override
//            protected void apply(RvViewHolder vh, String value, int position) {
//                vh.setText(R.id.tvRvItemSwipe, value);
//            }
//        };
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("Item " + i);
        }
        SwipeRvAdapter adapter = new SwipeRvAdapter(data);
//        adapter.data = data;
        rv.setAdapter(adapter);

        rv.addOnItemTouchListener(new OnRvItemClickListener(rv) {
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
            }

            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
            }
        });
    }
}