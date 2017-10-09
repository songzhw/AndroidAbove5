package cn.six.sup.rv.header;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.OnRvItemClickListener;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;

/**
 * Created by songzhw on 2016-08-12
 */
public class HeaderRvDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
//        rv.setLayoutManager(new GridLayoutManager(this, 3));
//        rv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("Item : " + i);
        }
        OneAdapter<String> adapter = new OneAdapter<String>(R.layout.item_rv_simple) {
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
        adapter.data = data;

        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.west_lake);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        HeaderWrapper wrapper = new HeaderWrapper(adapter);
        wrapper.headerView = iv;

        rv.setAdapter(wrapper);

        rv.addOnItemTouchListener(new OnRvItemClickListener(rv) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                System.out.println("szw : click " + vh.getAdapterPosition());
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
            }
        });

    }
}
