package cn.six.sup.rv.load_more;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.rv.RvConstants;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.OnRvItemClickListener;
import cn.six.sup.rv.load_more.mock.MockInfo;
import cn.six.sup.rv.load_more.mock.MockTask;
import cn.six.sup.rv.one_adapter.OneAdapter;

import cn.six.sup.R;


public class LoadMoreRvDemo extends AppCompatActivity implements MockTask.IPost {
    private LoadMoreRvDemo self;
    private RecyclerView rv;
    private OneAdapter<String> adapter;
    private FooterWrapper wrapper;

    private RecyclerView.OnScrollListener listener;
    private boolean hasMore = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.view_load_more);
        setContentView(R.layout.activity_recycler);
        self = this;

        rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
//        rv.setLayoutManager(new GridLayoutManager(this, 3));
//        rv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));


        adapter = new OneAdapter<String>(R.layout.item_rv_simple) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                TextView tvItem = vh.getView(R.id.tv_rv_simple_item);
                tvItem.setText("[szw -- " + data.get(position) + "]");
                if (position % 2 == 1) {
                    tvItem.setBackgroundColor(0xffC7EDCC);
                } else {
                    tvItem.setBackgroundColor(0xffffffff);
                }
            }
        };
        wrapper = new FooterWrapper(adapter);
        View loadMoreView = getLayoutInflater().inflate(R.layout.view_load_more, null);
        wrapper.footView = loadMoreView;
        rv.setAdapter(wrapper);

        rv.addOnItemTouchListener(new OnRvItemClickListener(rv) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int type = vh.getItemViewType();
                System.out.println("szw : click " + vh.getAdapterPosition()+" ; type = "+type);
                if(type == RvConstants.TYPE_FOOTER){
                    // TODO
                }
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
            }
        });

        // TODO may expand to grid and stagger
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
    public void onResp(MockInfo info) {
        // TODO if(info == null) or if(resp.isFailed()) --> footer.text="click to load", footer.pb = gone
        // TODO footer.onClick = startMockTask()
        List<String> data = info.data;
        hasMore = info.hasMore;
        adapter.data.addAll(data);
        System.out.println("szw onResp() : " + data.size());
        wrapper.notifyDataSetChanged();
    }


    private void hideFooter() {
        wrapper.footView = null;
        wrapper.notifyItemRemoved(adapter.getItemCount());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rv.removeOnScrollListener(listener);
    }


}