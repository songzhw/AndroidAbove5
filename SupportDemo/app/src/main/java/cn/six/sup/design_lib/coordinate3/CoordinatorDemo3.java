package cn.six.sup.design_lib.coordinate3;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.dragdrop.DragDropRvAdapter;

/**
 * Created by songzhw on 2016-07-17
 */
public class CoordinatorDemo3 extends AppCompatActivity {
    private RecyclerView rv;
    private DragDropRvAdapter adapter;

    private LinearLayout llayBottomSheet;
    private BottomSheetBehavior<LinearLayout> behavior;
    private TextView tvOutterBottomTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_three);

        List<String> list = new ArrayList<>(30);
        for (int i = 0; i < 30; i++) {
            list.add("Position " + i);
        }
        adapter = new DragDropRvAdapter(list);

        rv = (RecyclerView) findViewById(R.id.rvCoordinator3);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        tvOutterBottomTitle = (TextView) findViewById(R.id.tvOutterBottomTitle);

        llayBottomSheet = (LinearLayout) findViewById(R.id.llayCoordinate3Bottom);
        behavior = BottomSheetBehavior.from(llayBottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged( View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED ) {
                    tvOutterBottomTitle.setVisibility(View.VISIBLE);
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED ) {
                    tvOutterBottomTitle.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSlide( View bottomSheet, float slideOffset) {

            }
        });
    }

}
