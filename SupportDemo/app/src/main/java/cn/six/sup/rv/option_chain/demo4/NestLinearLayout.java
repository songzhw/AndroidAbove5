package cn.six.sup.rv.option_chain.demo4;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewGroupCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by songzhw on 2016-11-26
 *
 * 1. only three children: top, middle(scroll), bottom
 * 2.
 * 3.
 */

public class NestLinearLayout extends LinearLayout implements NestedScrollingParent {
    private View topView, scrollView;
    private int topHeight;

    public NestLinearLayout(Context context) {
        super(context);
    }

    public NestLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childrenCount = getChildCount();
        if(childrenCount != 2){
            throw new RuntimeException("NestLinearLayout must have only three children");
        }

        topView = getChildAt(0);
        scrollView = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // firstly, measure all the children
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // do not limit the topView's height
        topView.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        // after "super.onMeasure()", we can get the "getMeausredHeight()" now
        topHeight = topView.getMeasuredHeight();

        ViewGroup.LayoutParams lp = scrollView.getLayoutParams();
        lp.height = getMeasuredHeight() - topHeight;

        setMeasuredDimension(getMeasuredWidth(), topHeight + scrollView.getMeasuredHeight());
    }

    // =========================== NestScrollingParent : Scroll ===========================

    // @return true if this ViewParent accepts the nested scroll operation
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    // TODO: 2016-11-26 ????
    @Override
    public int getNestedScrollAxes() {
        return 0;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(target, dx, dy, consumed);
        boolean hiddenTop = dy > 0 && getScrollY() < topHeight;
        boolean showTop = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    // =========================== NestScrollingParent : Fling ===========================
    // @return true if this parent consumed or otherwise reacted to the fling
    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return true;
    }

}
