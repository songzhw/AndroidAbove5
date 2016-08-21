package cn.six.sup.rv.header;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.simple.RvSimpleAdapter;

/**
 * Created by songzhw on 2016-08-12
 */
public class HeaderRvDemo extends AppCompatActivity {
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
//        rv.setLayoutManager(new GridLayoutManager(this, 3));
//        rv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        List<String> data = new ArrayList<>();
        for (int i = 0 ; i < 50; i++){
            data.add("Item : "+i);
        }
        RvSimpleAdapter adapter = new RvSimpleAdapter();
        adapter.data = data;

        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.west_lake);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        HeaderWrapper wrapper = new HeaderWrapper(adapter);
        wrapper.headerView = iv;


        rv.setAdapter(wrapper);

    }
}
