package cn.six.sup.rv.group_adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.group_adapter.entity.CitiesRepo;
import cn.six.sup.rv.group_adapter.entity.City;
import cn.six.sup.rv.group_adapter.entity.Province;

/**
 * Created by songzhw on 2016-08-15
 */
public class GroupRvDemo extends Activity {
    private RecyclerView rv;
    private GroupRvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        rv = (RecyclerView) findViewById(R.id.rv_data);
        rv.setHasFixedSize(true);

        adapter = new GroupRvAdapter<Province, City>(R.layout.item_rv_group_title, R.layout.item_rv_group_content) {

            @Override
            protected void applyGroup(RvViewHolder vh, Province item, int position) {
                vh.setText(R.id.text, "[Province3] : "+ item.name);
            }

            @Override
            protected void applyChild(RvViewHolder vh, City item, int position) {
                vh.setText(R.id.text, "    City3  Name = "+ item.name);
                vh.setSrc(R.id.image, R.drawable.right_arrow);
            }
        };
        adapter.setData(CitiesRepo.country());

        rv.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        rv.setLayoutManager(layout);
    }
}
