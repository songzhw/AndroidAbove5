package cn.six.sup.rv.swipe_refresh2;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.load_more.deprecated.ILoadMoreListener;
import cn.six.sup.rv.load_more.deprecated.LoadMoreWrapper;
import cn.six.sup.rv.one_adapter.OneAdapter;

import cn.six.sup.R;

// "swipe to refresh" + Load More
// bug: 到达最后一页， 无法让loadMoreView消失！
// LoadMoreWrapper感觉走了偏锋。 用传统的rv.setOnScrollListener()方法可能更合适。
@Deprecated
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

        TextView loadMoreView = new TextView(this);
        loadMoreView.setText("Loading ...");
        loadMoreView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
        wrapper = new LoadMoreWrapper(adapter, this);
        wrapper.loadMoreView = loadMoreView;
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

    // mock
    private boolean hasMore = true; // initial value msut be true
    @Override
    public boolean hasMore() {
        System.out.println("szw hasMore() ");
        return hasMore;
    }

    private int currentPage = 1;
    @Override
    public void onLoadMore() {
        for(int i = 0; i < 10; i++){
            data.add("Item : "+currentPage+""+i);
        }
        currentPage++;
        if(currentPage >= 3){
            hasMore = false;
        }

        adapter.data = data;
        handler.post(new Runnable() {
            public void run() {
                wrapper.notifyDataSetChanged();
            }
        });
    }
}