package cn.six.sup.rv.option_chain.demo2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;

public class ExRvDemo02 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_rv_one);

        TextView tv = (TextView) findViewById(R.id.tvEx01);
        tv.setText("Demo 002");


        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            numbers.add("item " + i);
        }

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvEx01);
        ExRvAdapter02 adapter = new ExRvAdapter02(this, numbers);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

    }
}
