package cn.six.sup.rv.swipe_refresh3;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;

import cn.six.sup.R;

// Header +  "swipe to refresh" + Load More
public class SwipeRefreshRvActivity03 extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout slay;
    private RecyclerView rv;
    private OneAdapter<String> adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_rv);

        slay = (SwipeRefreshLayout) findViewById(R.id.slayRefresh);
        slay.setOnRefreshListener(this);

        rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new OneAdapter<String>(R.layout.item_rv_cards) {
            @Override
            protected void apply(RvViewHolder vh, String value, int position) {
                vh.setText(R.id.tv_rv_card_item, value);
            }
        };
        data = new ArrayList<>();
        for (int i = 0 ; i < 10; i++){
            data.add("Item : "+i);
        }
        adapter.data = data;
        rv.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        System.out.println("szw refresh()");
        // Mock the http
        handler.sendEmptyMessageDelayed(11, 5000);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 11){
                data.add(0, "Item : "+ System.currentTimeMillis());
                adapter.notifyItemInserted(0);
                rv.smoothScrollToPosition(0); // move to item 0 for better UX
                slay.setRefreshing(false);
            }
        }
    };
}