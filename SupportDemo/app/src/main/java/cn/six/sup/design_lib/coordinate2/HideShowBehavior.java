package cn.six.sup.design_lib.coordinate2;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by songzhw on 2016-07-11
 */
public class HideShowBehavior extends CoordinatorLayout.Behavior<View> {

    public HideShowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 开始滑动时调用一次。 已经在滑动了就不再多次重复调用了。就像action_down一样。不是action_move。
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        // "child" == fab, "directTargetChild" == "target" == SwipeRefreshLayout, "nestedScrollAxes" == 2

        //判断是否竖直滚动
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;

    }

    //
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        // onStartNestedScroll()指定了方向了， 这个方法才有可能被响应
        // "child" == fab, "target" == SwipeRefreshLayout

        //dy大于0是向上滚动 小于0是向下滚动
        if(dy >= 0) {
            child.setVisibility(View.GONE);
        } else {
            child.setVisibility(View.VISIBLE);
        }
    }
}
