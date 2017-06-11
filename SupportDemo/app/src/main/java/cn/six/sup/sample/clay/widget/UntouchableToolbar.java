package cn.six.sup.sample.clay.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class UntouchableToolbar extends Toolbar {
    public UntouchableToolbar(Context context) {
        super(context);
    }

    public UntouchableToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UntouchableToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
