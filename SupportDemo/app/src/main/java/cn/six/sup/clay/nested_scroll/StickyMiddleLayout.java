package cn.six.sup.clay.nested_scroll;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.support.v4.view.NestedScrollingParent;


public class StickyMiddleLayout extends LinearLayout implements NestedScrollingParent {
    public StickyMiddleLayout(Context context) {
        super(context);
    }

    public StickyMiddleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

}
