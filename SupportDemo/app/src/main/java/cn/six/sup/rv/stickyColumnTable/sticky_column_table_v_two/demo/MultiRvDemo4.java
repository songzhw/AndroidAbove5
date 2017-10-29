package cn.six.sup.rv.stickyColumnTable.sticky_column_table_v_two.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.stickyColumnTable.sticky_column_table_v_two.IStickyColumnTableInflater2;
import cn.six.sup.rv.stickyColumnTable.sticky_column_table_v_two.StickyColumnTableAdapter2;
import cn.six.sup.rv.stickyColumnTable.sticky_column_table_v_two.StickyColumnTableView2;


public class MultiRvDemo4 extends Activity implements IStickyColumnTableInflater2<String> {
    public static final int HEIGHT = 15;
    public static final int WIDTH = 7; // need to match with "app:sctColumnNumber=7" in xml

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_multi_rv_four);

        List<String> dataLeft = new ArrayList<>();
        for (int i = 1; i <= HEIGHT; i++) {
            dataLeft.add("" + i);
        }

        List<String> dataRight = new ArrayList<>();
        int sum = HEIGHT * WIDTH;
        for (int i = 1; i <= sum; i++) {
            dataRight.add("" + i);
        }

        StickyColumnTableAdapter2<String> adapter = new StickyColumnTableAdapter2<>(dataLeft, dataRight);
        StickyColumnTableView2<String> tableView = (StickyColumnTableView2<String>) findViewById(R.id.sctv_demo3);
        tableView.setAdapter(adapter);
        tableView.setBinder(this);
        tableView.refresh(false);
    }

    @Override
    public void bindLeft(RvViewHolder vh, String s, int position) {
        vh.setText(R.id.tvItemSymbol, s);
    }

    @Override
    public void bindRight(RvViewHolder vh, String s, int position) {
        vh.setText(R.id.tvItemDetails, s);
    }
}

