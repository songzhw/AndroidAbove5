package cn.six.sup.rv.swipe_card;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.card_stack.CardsLayoutManager;
import cn.six.sup.rv.simple.RvSimpleAdapter;

/**
 * Created by songzhw on 2016-11-12
 */
// TODO: 2016-11-12 later
public class SwipeCardRvDemo extends Activity {
    private List<String> aData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_demo);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setLayoutManager(new SwipeCardLayoutManager());

        RvSimpleAdapter adapter = new RvSimpleAdapter();

        aData = new ArrayList<>();
        for(int i = 0 ; i< 20; i++){
            aData.add( "Item "+i );
        }
        adapter.data = aData;
        rv.setAdapter(adapter);

    }
}
