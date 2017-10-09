package cn.six.sup.rv.option_chain.demo1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import cn.six.sup.R;


/**
 * Created by songz2 on 11/25/2016.
 */
public class ExRvDemo01 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_rv_one);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvEx01);
        ExRvAdapter01 adapter = new ExRvAdapter01();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

    }
}
