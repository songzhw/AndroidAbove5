package cn.six.sup.rv.composition.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.composition.BaseComposedAdapter;
import cn.six.sup.rv.composition.BaseRow;
import cn.six.sup.rv.composition.demo.data.EntityHeader;
import cn.six.sup.rv.composition.demo.data.EntityTwo;
import cn.six.sup.rv.composition.demo.data.IEntity;

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
    public void refreshList(List<IEntity> data) {
        List<BaseRow> items = new ArrayList<>();

        for(IEntity raw : data){
            int type = raw.getType();
            if(BaseRow.TYPE_HEADER == type){
                EntityHeader header = (EntityHeader) raw;
                items.add(new HeaderRow(header.title, header.caption));
            } else if (BaseRow.TYPE_TWO_TEXT == type){
                EntityTwo two = (EntityTwo) raw;
                items.add(new TwoTextRow(two.left, two.right));
            }
        }



        BaseComposedAdapter adapter = new BaseComposedAdapter(items);
        rv.setAdapter(adapter);
    }
}