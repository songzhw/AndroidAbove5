package cn.six.sup.rv.card_stack;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;
import cn.six.sup.rv.swipe_card.SwipeCardLayoutManager;

public class SwipeCardRvDemo extends Activity {
    private List<Integer> aData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_demo);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setLayoutManager(new CardsLayoutManager());

        OneAdapter<Integer> adapter = new OneAdapter<Integer>(R.layout.item_card_stack) {
            @Override
            protected void apply(RvViewHolder vh, Integer integer, int position) {
                vh.setSrc(R.id.ivCards, integer);
                vh.setText(R.id.tvCards, "This is Item "+position);
            }
        };


        aData = new ArrayList<>();
        aData.add(R.drawable.photo1);
        aData.add(R.drawable.photo2);
        aData.add(R.drawable.photo3);
        aData.add(R.drawable.photo4);
        aData.add(R.drawable.photo5);
        aData.add(R.drawable.photo6);
        adapter.data = aData;
        rv.setAdapter(adapter);

    }
}