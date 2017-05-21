package cn.six.sup.rv.composition.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.composition.BaseComposedAdapter;
import cn.six.sup.rv.composition.BaseRow;


public class ComposedRvDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<BaseRow> items = new ArrayList<>();
        for(int i = 0 ; i < 5; i++) {
            items.add(new HeaderRow("t1"+i, "description1"));
            items.add(new TwoTextRow("time: ", "2017-05-21"));
            items.add(new HeaderRow("title2"+i, "caption 0000000000002"));
            items.add(new TwoTextRow("location: ", "US MountainView"));
            items.add(new TwoTextRow("price: ", "$300,888,666,222"));
        }

        BaseComposedAdapter adapter = new BaseComposedAdapter(items);
        rv.setAdapter(adapter);
    }
}