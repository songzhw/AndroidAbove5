package cn.six.sup.rv.cooridinate_rv_rv_2.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.cooridinate_rv_rv_2.IStickyColumnTableInflater;
import cn.six.sup.rv.cooridinate_rv_rv_2.StickyColumnTableAdapter;
import cn.six.sup.rv.cooridinate_rv_rv_2.StickyColumnTableView;

public class MultiRvDemo2 extends Activity implements IStickyColumnTableInflater<String>{
    public static final int HEIGHT = 15;
    public static final int WIDTH = 7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_multi_rv_two);

        List<String> dataLeft = new ArrayList<>();
        for (int i = 1; i <= HEIGHT; i++) {
            dataLeft.add("" + i);
        }

        List<String> dataRight = new ArrayList<>();
        int sum = HEIGHT * WIDTH;
        for (int i = 1; i <= sum; i++) {
            dataRight.add("" + i);
        }

        StickyColumnTableAdapter<String> adapter = new StickyColumnTableAdapter<>(dataLeft, dataRight);
        StickyColumnTableView tableView = (StickyColumnTableView) findViewById(R.id.sctv_demo2);
        tableView.setAdapter(adapter);
        tableView.refresh();
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

