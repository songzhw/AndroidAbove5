package cn.six.sup.rv.swipe_refresh2;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.OnRvItemClickListener;
import cn.six.sup.rv.load_more.ILoadMoreListener;
import cn.six.sup.rv.load_more.LoadMoreWrapper;
import cn.six.sup.rv.one_adapter.OneAdapter;

import cn.six.sup.R;

// only "swipe to refresh"
public class SwipeRefreshRvActivity02 extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, ILoadMoreListener {
    private SwipeRefreshLayout slay;
    private RecyclerView rv;
    private OneAdapter<String> adapter;
    private LoadMoreWrapper wrapper;
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
//        rv.setAdapter(adapter);

        TextView tv = new TextView(this);
        tv.setText("Loading ...");
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
        wrapper = new LoadMoreWrapper(adapter, this);
        wrapper.loadMoreView = tv;
        rv.setAdapter(wrapper);
    }

    @Override
    public void onRefresh() {
        // Mock the http
        handler.sendEmptyMessageDelayed(11, 1000);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 11){
                data.add(0, "Item : "+ System.currentTimeMillis());
                adapter.data = data;
                wrapper.notifyDataSetChanged(); // important!
                rv.smoothScrollToPosition(0); // move to item 0 for better UX
                slay.setRefreshing(false);
            }
        }
    };

    @Override
    public boolean hasMore() {
        return false;
    }

    @Override
    public void onLoadMore() {

    }
}