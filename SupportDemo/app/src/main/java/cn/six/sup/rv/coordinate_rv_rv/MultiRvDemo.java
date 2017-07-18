package cn.six.sup.rv.coordinate_rv_rv;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;

public class MultiRvDemo extends Activity {
    public static final int HEIGHT = 15;
    public static final int WIDTH = 7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_multi_rv);

        List<String> dataLeft = new ArrayList<>();
        for(int i = 1; i <= HEIGHT; i++){
            dataLeft.add(""+i);
        }

        List<String> dataRight = new ArrayList<>();
        int sum = HEIGHT * WIDTH;
        for(int i = 1; i <= sum; i++){
            dataRight.add(""+i);
        }


        RecyclerView rvLeft = (RecyclerView)findViewById(R.id.rvMultiRvLeft);
        rvLeft.setLayoutManager(new LinearLayoutManager(this));
        rvLeft.setAdapter(new OneAdapter<String>(R.layout.item_left, dataLeft) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                vh.setText(R.id.tvItemSymbol, s);
            }
        });

        RecyclerView rvRight = (RecyclerView)findViewById(R.id.rvMultiRvRight);
        rvRight.setLayoutManager(new GridLayoutManager(this, WIDTH));
        rvRight.setAdapter(new OneAdapter<String>(R.layout.item_right, dataRight) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                vh.setText(R.id.tvItemDetails, s);
            }
        });

    }
}

