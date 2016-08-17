package cn.six.sup.rv.cards;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;


// RecyclerView + CardView
public class RvCardsActivity extends Activity {
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        rv = (RecyclerView) findViewById(R.id.rv_data);

        // improve performance if you know that changes in content
        // do not change the size of the RecyclerView
        rv.setHasFixedSize(true);

//        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        rv.setLayoutManager(layout);

        List<String> data = new ArrayList<>();
        data.add("13");data.add("ab");data.add("test");
        RvCardsAdapter adapter = new RvCardsAdapter();
        adapter.data = data;
        rv.setAdapter(adapter);

    }
}
