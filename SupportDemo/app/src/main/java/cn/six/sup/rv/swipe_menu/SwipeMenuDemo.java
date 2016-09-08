package cn.six.sup.rv.swipe_menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;

import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.OnRvItemClickListener;
import cn.six.sup.rv.one_adapter.OneAdapter;
import cn.six.sup.rv.header.HeaderWrapper;

import cn.six.sup.R;

public class SwipeMenuDemo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_swipe_menu_demo);
    }
}
