package cn.six.sup.rv.load_more;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.OnRvItemClickListener;
import cn.six.sup.rv.one_adapter.OneAdapter;

import cn.six.sup.R;


public class LoadMoreRvDemo extends AppCompatActivity {
    private RecyclerView rv;
    private OneAdapter<String> adapter;
    private FooterWrapper wrapper;

    private View loadMoreView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.view_load_more);
        setContentView(R.layout.activity_recycler);

        rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
//        rv.setLayoutManager(new GridLayoutManager(this, 3));
//        rv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        List<String> data = new ArrayList<>();
        for (int i = 0 ; i < 10; i++){
            data.add("Item : "+i);
        }
        final OneAdapter<String> adapter = new OneAdapter<String>(R.layout.item_rv_simple) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                TextView tvItem = vh.getView(R.id.tv_rv_simple_item);
                tvItem.setText("[szw -- " + data.get(position)+"]" );
                if(position % 2 == 1){
                    tvItem.setBackgroundColor(0xffC7EDCC);
                } else {
                    tvItem.setBackgroundColor(0xffffffff);
                }
            }
        };
        adapter.data = data;
        wrapper = new FooterWrapper(adapter);
        loadMoreView = getLayoutInflater().inflate(R.layout.view_load_more, null);
        wrapper.footView = loadMoreView;
        rv.setAdapter(wrapper);

        rv.addOnItemTouchListener(new OnRvItemClickListener(rv) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                System.out.println("szw : click "+vh.getAdapterPosition());
                wrapper.footView = null;
                wrapper.notifyItemRemoved(adapter.getItemCount());
            }
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
            }
        });

        rv.addOnScrollListener(listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rv.removeOnScrollListener(listener);
    }

    private RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

}