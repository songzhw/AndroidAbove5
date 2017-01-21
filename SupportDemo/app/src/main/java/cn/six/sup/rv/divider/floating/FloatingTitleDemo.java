package cn.six.sup.rv.divider.floating;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;

public class FloatingTitleDemo extends Activity implements IFloatingGroupCallback {
    private RecyclerView rv;
    private OneAdapter adapter;
    private List<FloatingModel> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_demo);

        rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setLayoutManager(new LinearLayoutManager(this));

        rv.addItemDecoration(new FloatingTitleDivider(this));

        adapter = new OneAdapter<FloatingModel>(R.layout.item_rv) {
            @Override
            protected void apply(RvViewHolder vh, FloatingModel value, int position) {
                vh.setText(R.id.tv_rv_item, value.text);
            }
        };
        data = new ArrayList<>();
        for (int i = 0 ; i < 26; i++){
            String title = "Group "+ (i / 10);
            data.add(new FloatingModel("Item : "+i, title));
        }
        adapter.data = data;
        rv.setAdapter(adapter);
    }

    @Override
    public String getGroup(int position) {
        return data.get(position).title;
    }


}