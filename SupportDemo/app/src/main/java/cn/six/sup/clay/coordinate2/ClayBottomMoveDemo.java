package cn.six.sup.clay.coordinate2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.dragdrop.simple.DragDropRvAdapter;

/**
 * Created by songzhw on 2016-07-11
 */
public class ClayBottomMoveDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_two);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Custom Behavior 002");
        setSupportActionBar(toolbar);

        List<String> list = new ArrayList<>(30);
        for (int i = 0; i < 30; i++) {
            list.add("Position " + i);
        }
        DragDropRvAdapter adapter = new DragDropRvAdapter(list);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvCoordinator2);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
}
