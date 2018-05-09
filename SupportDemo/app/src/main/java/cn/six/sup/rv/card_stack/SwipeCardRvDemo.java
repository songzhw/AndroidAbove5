package cn.six.sup.rv.card_stack;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.OneAdapter;

public class SwipeCardRvDemo extends Activity {
    private List<Integer> aData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_demo);

        OneAdapter<Integer> adapter = new OneAdapter<Integer>(R.layout.item_card_stack) {
            @Override
            protected void apply(RvViewHolder vh, Integer integer, int position) {
                vh.setSrc(R.id.ivCards, integer);
                vh.setText(R.id.tvCards, "This is Item " + position);
            }
        };

        CardStackTouchCallback<Integer> callback = new CardStackTouchCallback<>(adapter, new ICardStackActionListener<Integer>() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder vh, float ratio, @CardsDirection int direction) {
                System.out.println("szw swiping " + direction);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder vh, Integer integer, @CardsDirection int direction) {
                System.out.println("szw swiped " + direction);
            }

            @Override
            public void onSwipedClear() {
                System.out.println("szw clear");
            }
        });

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvRefresh);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        rv.setLayoutManager(new CardsLayoutManager(rv, touchHelper));
        touchHelper.attachToRecyclerView(rv);


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