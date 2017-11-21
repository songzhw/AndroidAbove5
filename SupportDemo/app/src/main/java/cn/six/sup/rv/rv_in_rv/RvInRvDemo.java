
package cn.six.sup.rv.rv_in_rv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.composition.BaseComposedAdapter;
import cn.six.sup.rv.composition.BaseRow;
import cn.six.sup.rv.composition.demo.IComposedRvView;

public class RvInRvDemo extends AppCompatActivity implements IComposedRvView {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        rv = findViewById(R.id.rvRefresh);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        RvInRvPresenter presenter = new RvInRvPresenter(this);
        presenter.fetchData();
    }

    @Override
    public void refreshList(List<BaseRow> items) {
        BaseComposedAdapter adapter = new BaseComposedAdapter(items);
        rv.setAdapter(adapter);
    }
}

