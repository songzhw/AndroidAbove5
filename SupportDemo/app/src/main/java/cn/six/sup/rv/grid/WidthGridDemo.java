package cn.six.sup.rv.grid;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;


public class WidthGridDemo extends Activity {

    private RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_demo);

        rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setLayoutManager(new GridLayoutManager(this, 4));

        List<IWidthType> data = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            data.add(new WidthNumber(""+ (100+i)));
            data.add(new WidthNumber(""+ (200+i)));
            data.add(new WidthNumber(""+ (300+i)));
            data.add(new WidthAction());
        }

        WidthGridAdapter adapter = new WidthGridAdapter();
        adapter.data = data;
        rv.setAdapter(adapter);
    }
}
