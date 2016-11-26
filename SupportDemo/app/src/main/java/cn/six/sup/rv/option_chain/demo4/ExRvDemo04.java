package cn.six.sup.rv.option_chain.demo4;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.option_chain.demo2.NumbersAdapter;


public class ExRvDemo04 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_rv_four);

        List<String> numbers = new ArrayList<>();
        for(int i = 0; i < 400; i++){
            numbers.add("item "+i);
        }

        RecyclerView rv = (RecyclerView)findViewById(R.id.rvEx04);
        NumbersAdapter adapter = new NumbersAdapter(this, numbers);
        rv.setLayoutManager(new GridLayoutManager(this, 4));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

    }
}