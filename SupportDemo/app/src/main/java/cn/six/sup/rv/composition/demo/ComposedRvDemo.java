package cn.six.sup.rv.composition.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.composition.BaseComposedAdapter;
import cn.six.sup.rv.composition.BaseRow;

public class ComposedRvDemo extends AppCompatActivity implements IComposedRvView {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ComposedRvPresenter presenter = new ComposedRvPresenter(this);
        presenter.getData();
    }

    @Override
    public void refreshList(List<BaseRow> items) {
        BaseComposedAdapter adapter = new BaseComposedAdapter(items);
        rv.setAdapter(adapter);
    }
}