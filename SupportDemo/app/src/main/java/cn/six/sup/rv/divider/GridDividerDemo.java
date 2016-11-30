package cn.six.sup.rv.divider;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.option_chain.demo2.NumbersAdapter;

public class GridDividerDemo extends Activity {
    public static final int COLUMN_COUNT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_divider_one);

        List<String> numbers = new ArrayList<>();
        for(int i = 0; i < 500; i++){
            numbers.add("$ "+i);
        }

        RecyclerView rv = (RecyclerView)findViewById(R.id.rvDivider01);
        rv.setLayoutManager(new GridLayoutManager(this, COLUMN_COUNT));
        rv.setHasFixedSize(true);

        Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_grid);
//        GridDivider03 divider = new GridDivider03(dividerDrawable);
        Drawable specialDrawable = ContextCompat.getDrawable(this, R.drawable.divider_grid_highlight);
        HighlightGridDivider divider = new HighlightGridDivider(dividerDrawable, specialDrawable, 25, 29);
        rv.addItemDecoration(divider);

        NumbersAdapter adapter = new NumbersAdapter(this, numbers);
        rv.setAdapter(adapter);

    }
}