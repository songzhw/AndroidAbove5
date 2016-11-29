package cn.six.sup.rv.divider;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.option_chain.demo2.NumbersAdapter;

public class GridDividerDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_divider_one);

        List<String> numbers = new ArrayList<>();
        for(int i = 0; i < 500; i++){
            numbers.add("$ "+i);
        }

        RecyclerView rv = (RecyclerView)findViewById(R.id.rvDivider01);
        rv.setLayoutManager(new GridLayoutManager(this, 5));
        rv.setHasFixedSize(true);

        GridDividerItemDecoration divider = new GridDividerItemDecoration();
        divider.setColor(0xff1c1c1c);
        divider.setSize(4);
        rv.addItemDecoration(divider);

        NumbersAdapter adapter = new NumbersAdapter(this, numbers);
        rv.setAdapter(adapter);

    }
}