package cn.six.sup.rv.coordinate_rv_rv_in_rv;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.ItemView;
import cn.six.sup.rv.OneTypesAdapter;
public class RvWrapMultiRvDemo extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_rv_wrap_multi_rv);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rvWrapMultiRv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<ItemView> data = new ArrayList<>();
        data.add(new MultiRvRow());
        data.add(new SimpleMenuRow());
        data.add(new SimpleMenuRow());
        data.add(new SimpleMenuRow());
        data.add(new SimpleMenuRow());
        data.add(new SimpleMenuRow());
        data.add(new SimpleMenuRow());
        data.add(new SimpleMenuRow());
        rv.setAdapter(new OneTypesAdapter(data));

    }

}


