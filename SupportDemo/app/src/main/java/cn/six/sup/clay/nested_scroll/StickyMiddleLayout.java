package cn.six.sup.clay.nested_scroll;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import java.lang.reflect.Method;


public class StickyMiddleLayout extends LinearLayout implements NestedScrollingParent {
    // OverScroller class encapsulates scrolling with the ability to overshoot the bounds of a scrolling operation.
    // This class is a drop-in replacement for android.widget.Scroller in most cases.
    private OverScroller scroller;

    private int topViewHeight;

    public StickyMiddleLayout(Context context) {
        super(context);
        init(context);
    }

    public StickyMiddleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context ctx) {
        scroller = new OverScroller(ctx);
    }

    private int getHasVirtualKey() {
        int dpi = 0;
        Activity actv = (Activity) this.getContext();
        Display display = actv.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    private int getStatusHeight() {
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight1;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec); //不加这一句, 后面的childView.getMeasureHeight()的值就是0 !

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        System.out.println("szw phone1 : " + heightPixels);
        System.out.println("szw phone2 : " + getHasVirtualKey());
        System.out.println("szw phone3 : " + getStatusHeight());

        System.out.println("szw measure : " + getMeasuredHeight());
        ViewGroup.LayoutParams lp = getChildAt(2).getLayoutParams();
        lp.height = getMeasuredHeight() - getChildAt(1).getMeasuredHeight();

        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (mode == MeasureSpec.AT_MOST) {
            height = 0;
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                int childHeight = getChildAt(i).getMeasuredHeight();
                System.out.println("szw measure child height = " + childHeight);
                height += childHeight;
            }
        }

        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, mode);
        setMeasuredDimension(widthMeasureSpec, newHeightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        View topChildView = getChildAt(0);
        topViewHeight = topChildView.getMeasuredHeight();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        // onStartNestedScroll()中要指明我要关注哪个滑动方向
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    /**
     * @param dy : 往上推, 让topView不见时, dy是正数; 反之, 是负数
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        // 滑动起来了, 我作为父layout, 要不要消耗掉这些滑动, 来做我自己相关的动画
        int scrollY = getScrollY();
        boolean isTopNotTotallyHidden = dy > 0 && scrollY < topViewHeight;
        boolean isTopNotTotallyShown = dy < 0 && scrollY > 0
                && !ViewCompat.canScrollVertically(target, -1); //negative num is scrolling up
        if (isTopNotTotallyHidden || isTopNotTotallyShown) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }


    /**
     * @return true if this parent consumed the fling ahead of the target view
     */
    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        // topView已经不见, 那parent就不管了, 要滑动与fling也是给带滑动的子view了, 所以这时要返回false
        if (getScrollY() > topViewHeight) {
            return false;
        }

        fling((int) velocityY);
        return true;  // 反之, 那我们parent就要接掌所有的滑动, 所以这里要返回true
    }

    private void fling(int velocityY) {
        scroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, topViewHeight);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(0, scroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) y = 0;
        if (y > topViewHeight) y = topViewHeight;
        if (y != getScrollY()) super.scrollTo(x, y);
    }


}

