package cn.six.sup.rv.one_adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;

/**
 * Created by songzhw on 2016-08-13
 */
public class RvOneAdaterDemo extends Activity {
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        rv = (RecyclerView) findViewById(R.id.rv_data);
        rv.setHasFixedSize(true);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        rv.setLayoutManager(layout);

        List<String> data = new ArrayList<>();
        for (int i = 0 ; i < 50; i++){
            data.add("Item : "+i);
        }

        OneAdapter<String> adapter = new OneAdapter<String>(R.layout.item_rv_simple) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                TextView tv = vh.getView(R.id.tv_rv_simple_item);
                tv.setText("Position "+position+" : "+s);
                if(position % 2 == 1){
                    tv.setBackgroundColor(0xffC7EDCC);
                } else {
                    tv.setBackgroundColor(0xffffffff);
                }
            }
        };
        adapter.data = data;

        rv.setAdapter(adapter);

    }
}
