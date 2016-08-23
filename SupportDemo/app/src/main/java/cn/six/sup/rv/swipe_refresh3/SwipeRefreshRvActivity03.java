package cn.six.sup.rv.swipe_refresh3;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.load_more.FooterWrapper;
import cn.six.sup.rv.load_more.LoadMoreScrollListener;
import cn.six.sup.rv.load_more.mock.MockInfo;
import cn.six.sup.rv.load_more.mock.MockTask;
import cn.six.sup.rv.one_adapter.OneAdapter;

import cn.six.sup.R;

// Header +  "swipe to refresh" + Load More
public class SwipeRefreshRvActivity03 extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, MockTask.IPost {
    private SwipeRefreshLayout slay;
    private SwipeRefreshRvActivity03 self;
    private RecyclerView rv;
    private OneAdapter<String> adapter;
    private FooterWrapper wrapper;
    private List<String> data;
    private View loadMoreView;
    private LoadMoreScrollListener listener;
    private boolean hasMore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_rv);
        self = this;

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
        wrapper = new FooterWrapper(adapter);
        loadMoreView = getLayoutInflater().inflate(R.layout.view_load_more, null);
        wrapper.footView = loadMoreView;
        rv.setAdapter(wrapper);

        listener = new LoadMoreScrollListener((LinearLayoutManager) rv.getLayoutManager()) {
            @Override
            public void onLoadMore(int page) {
                System.out.println("szw onLoadMore["+page+"]");

                if(!hasMore){
                    Toast.makeText(self,"No more infos!", Toast.LENGTH_SHORT).show();
                    hideFooter();
                    return;
                }

                MockTask http = new MockTask(self);
                http.execute(page);
            }
        };
        rv.addOnScrollListener(listener);

        MockTask http = new MockTask(this);
        http.execute(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rv.removeOnScrollListener(listener);
    }

    @Override
    public void onResp(MockInfo info) {
        // TODO if(info == null) or if(resp.isFailed()) --> footer.text="click to load", footer.pb = gone
        // TODO footer.onClick = startMockTask()
        data = info.data;
        hasMore = info.hasMore;
        adapter.data.addAll(data);
        System.out.println("szw onResp() : " + data.size());
        wrapper.notifyDataSetChanged();
    }

    private void hideFooter() {
        wrapper.footView = null;
        wrapper.notifyItemRemoved(adapter.getItemCount());
    }

    private void showFooter(){
        wrapper.footView = loadMoreView;
    }

    @Override
    public void onRefresh() {
        System.out.println("szw refresh()");
        // Mock the http
        handler.sendEmptyMessageDelayed(11, 1000);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 11){
                data.clear();
                for(int i = 0; i< 10; i++){
                    data.add("New Item : "+i);
                }
                adapter.data = data;

                // help reset the footer
                showFooter();
                listener.reset();
                hasMore = true;

                wrapper.notifyDataSetChanged();
                rv.smoothScrollToPosition(0); // move to item 0 for better UX
                slay.setRefreshing(false);
            }
        }
    };
}