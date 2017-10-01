package cn.six.sup.rv.custom_layout_mgr.linear;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;

public class FirstLayoutRvDemo extends Activity {
    private List<String> aData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_demo);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new FirstLayoutManager());

        aData = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            aData.add("Item " + i);
        }
        OneAdapter<String> adapter = new OneAdapter<String>(R.layout.item_rv_slash, aData){
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                vh.setText(R.id.tvSlash, s);
            }
        };
        rv.setAdapter(adapter);

    }
}