package cn.six.sup.clay.nested_scroll;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.support.v4.view.NestedScrollingParent;




public class StickyMiddleLayout extends LinearLayout implements NestedScrollingParent {
    private int topViewHeight;

    public StickyMiddleLayout(Context context) {
        super(context);
    }

    public StickyMiddleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View topChildView = getChildAt(0);
        topViewHeight = topChildView.getMeasuredHeight();
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        // onStartNestedScroll()中要指明我要关注哪个滑动方向
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    /**@param dy : 往上推, 让topView不见时, dy是正数; 反之, 是负数 */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        // 滑动起来了, 我作为父layout, 要不要消耗掉这些滑动, 来做我自己相关的动画
        int scrollY = getScrollY();
        System.out.println("szw scrollY = "+scrollY);
        boolean isTopNotTotallyHidden = dy > 0 && scrollY < topViewHeight;
        boolean isTopNotTotallyShown = dy < 0 && scrollY > 0
                && !ViewCompat.canScrollVertically(target, -1); //negative num is scrolling up
        if(isTopNotTotallyHidden || isTopNotTotallyShown) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return super.onNestedPreFling(target, velocityX, velocityY);
    }
}

