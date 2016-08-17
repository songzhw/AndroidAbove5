package cn.six.sup.rv.empty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.simple.RvSimpleAdapter;

/**
 * Created by songzhw on 2016-08-13
 *
 * @deprecated : the empty will not in the center of the Rv
 * this is not good for UI
 */
public class EmptyRvDemo extends AppCompatActivity {
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        rv = (RecyclerView) findViewById(R.id.rv_data);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
//        rv.setLayoutManager(new GridLayoutManager(this, 3));
//        rv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        List<String> data = new ArrayList<>();
        for (int i = 0 ; i < 20; i++){
            data.add("Item : "+i);
        }
        RvSimpleAdapter adapter = new RvSimpleAdapter();
//        adapter.data = data;

        View emptyView = getLayoutInflater().inflate(R.layout.item_rv_empty, null);
        EmptyWrapper wrapper = new EmptyWrapper(adapter);
        wrapper.emptyView = emptyView;


        rv.setAdapter(wrapper);

    }
}
