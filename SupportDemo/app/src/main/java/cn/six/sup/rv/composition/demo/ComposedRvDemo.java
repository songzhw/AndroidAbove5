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
        items.add(new HeaderRow("t1","description1"));
        items.add(new HeaderRow("title2","caption 0000000000002"));

        BaseComposedAdapter adapter = new BaseComposedAdapter(items);
        rv.setAdapter(adapter);
    }
}