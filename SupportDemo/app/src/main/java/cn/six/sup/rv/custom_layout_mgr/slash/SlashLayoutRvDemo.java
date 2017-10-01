package cn.six.sup.rv.custom_layout_mgr.slash;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;

public class SlashLayoutRvDemo extends Activity {
    private List<String> aData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_demo);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new SlashLayoutManager());

        SlashLayoutAdapter adapter = new SlashLayoutAdapter();

        aData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            aData.add("Item " + i);
        }
        adapter.data = aData;
        rv.setAdapter(adapter);

    }
}