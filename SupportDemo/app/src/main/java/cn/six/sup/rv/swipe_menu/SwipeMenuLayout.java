package cn.six.sup.rv.swipe_menu;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by songzhw on 2016-09-08
 */
public class SwipeMenuLayout extends FrameLayout {
    private ViewDragHelper dragger;
    private View contentView, menuView;
    private int width, menuWidth;

    public SwipeMenuLayout(Context context) {
        super(context);
    }

    public SwipeMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(0);
        menuView = getChildAt(1);
        dragger = ViewDragHelper.create(this, callback);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getMeasuredWidth();
        menuWidth = menuView.getMeasuredWidth();
        contentView.layout(left, top, right, bottom);
        menuView.layout(right, top, right + menuWidth, bottom);
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == contentView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if(left < -menuWidth){
                return -menuWidth;
            } else if(left > 0){
                return 0;
            }
            return left;
        }
    };

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragger.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragger.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (dragger.continueSettling(true)) {
            invalidate();
        }
    }
}
