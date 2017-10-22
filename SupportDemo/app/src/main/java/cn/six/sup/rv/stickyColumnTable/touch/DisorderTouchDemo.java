package cn.six.sup.rv.stickyColumnTable.touch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.composition.BaseComposedAdapter;
import cn.six.sup.rv.composition.BaseRow;
import cn.six.sup.rv.composition.ClothRow;
import cn.six.sup.rv.composition.demo.TwoTextRow;

public class DisorderTouchDemo extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rv;
    private SwipeRefreshLayout srlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disorder_touch_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        srlay = (SwipeRefreshLayout) findViewById(R.id.srlayRefresh);
        srlay.setOnRefreshListener(this);

        rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<BaseRow> rows = new ArrayList<>();
        rows.add(new StickyCoulmnTableRow());
        rows.add(new TwoTextRow("sing1","song1"));
        rows.add(new TwoTextRow("sing2","song2"));
        rows.add(new TwoTextRow("sing3","song3"));
        rows.add(new TwoTextRow("sing4","song4"));
        rows.add(new TwoTextRow("sing5","song5"));
        rows.add(new TwoTextRow("sing6","song6"));
        rows.add(new TwoTextRow("sing7","song7"));
        rows.add(new TwoTextRow("sing8","song8"));
        rows.add(new TwoTextRow("sing9","song9"));
        rows.add(new ClothRow("leisi","lei si", "Buy", null));
        rows.add(new ClothRow("trespassing","Test definition, the means by which the presence, quality, or genuineness of anything is determined; a means of trial", "Look", null));
        BaseComposedAdapter adapter = new BaseComposedAdapter(rows);
        rv.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(11, 2000);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 11){
                srlay.setRefreshing(false); // stop the refreshing
            }
        }
    };
}
