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
    private int menuWidth;

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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        menuWidth = menuView.getMeasuredWidth();
        contentView.layout(left, top, right, bottom);
        menuView.layout(right, top, right + menuWidth, bottom);

        System.out.println("szw layout MenuWidth = " + menuWidth);
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == contentView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (left < -menuWidth) {
                return -menuWidth;
            } else if (left > 0) {
                return 0;
            }
            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            System.out.println("szw left = " + left + " ; dx = " + dx);
            menuView.offsetLeftAndRight(dx);
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

/*
两个问题

1. 滑动过半, 应该自动就位

2. (bug) 第一项滑出菜单, 再垂直滑到第二屏, 因为复用的关系 , 第16项也变成滑出了菜单(但我是没有滑的, 只是复用view的关系引起了混乱)
 */