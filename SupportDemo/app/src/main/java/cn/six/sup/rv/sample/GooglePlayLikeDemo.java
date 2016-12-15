package cn.six.sup.rv.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

/**
 * Created by songzhw on 2016-12-14
 */

public class GooglePlayLikeDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_like);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setLayoutManager(new LinearLayoutManager(this));
        OneAdapter<List<Integer>> adapter = new OneAdapter<List<Integer>>(R.layout.item_play_like_middle) {
            @Override
            protected void apply(RvViewHolder vh, List<Integer> integers, int position) {
                applyInnerRv(vh, integers, position);
            }
        };

        List<List<Integer>> data = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            List<Integer> innerData = new ArrayList<>();
            for(int j = 0; j < 10; j++){
                if(j % 2 == 0) {
                    innerData.add(R.drawable.ic_launcher);
                } else {
                    innerData.add(R.drawable.in_icon);
                }
            }
            data.add(innerData);
        }
        adapter.data = data;
        rv.setAdapter(adapter);
    }

    private void applyInnerRv(RvViewHolder vh, List<Integer> integers, int position) {
        RecyclerView rvInner = vh.getView(R.id.rvMiddle);
        rvInner.setLayoutManager(new LinearLayoutManager(this, HORIZONTAL, false));
        OneAdapter<Integer> adapterInner = new OneAdapter<Integer>(R.layout.item_play_like) {
            @Override
            protected void apply(RvViewHolder vh, Integer integer, int position) {
                vh.setSrc(R.id.ivPlayLike, integer);
            }
        };
        adapterInner.data = integers;
        rvInner.setAdapter(adapterInner);
    }


}
