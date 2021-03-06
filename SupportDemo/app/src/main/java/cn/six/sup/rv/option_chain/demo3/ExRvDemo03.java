package cn.six.sup.rv.option_chain.demo3;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.option_chain.demo2.NumbersAdapter;

public class ExRvDemo03 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_rv_two);

        TextView tv = (TextView) findViewById(R.id.tvEx02);
        tv.setText("Demo 002");


        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            numbers.add("item " + i);
        }

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvEx02);
        NumbersAdapter adapter = new NumbersAdapter(this, numbers);
        rv.setLayoutManager(new GridLayoutManager(this, 4));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

    }
}