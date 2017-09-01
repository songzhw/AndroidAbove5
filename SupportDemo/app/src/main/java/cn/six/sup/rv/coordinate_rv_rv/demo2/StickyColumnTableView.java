package cn.six.sup.rv.coordinate_rv_rv.demo2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import cn.six.sup.R;

public class StickyColumnTableView extends LinearLayout {

    public StickyColumnTableView(Context context) {
        super(context);
        init(context);
    }

    public StickyColumnTableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context ctx) {
        this.setOrientation(HORIZONTAL);
        LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View leftView = inflater.inflate(R.layout.sub_multi_rv_left, this, false);
        View rightView = inflater.inflate(R.layout.sub_multi_rv_right, this, false);
        this.addView(leftView);
        this.addView(rightView);
    }

}
