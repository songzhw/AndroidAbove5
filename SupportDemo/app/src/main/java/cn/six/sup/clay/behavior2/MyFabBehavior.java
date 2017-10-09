package cn.six.sup.clay.behavior2;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by songzhw on 2016/2/25
 */
public class MyFabBehavior extends FloatingActionButton.Behavior {

    public MyFabBehavior(Context ctx, AttributeSet attrs) {
        super();
    }

    // 指明我们希望处理垂直方向的滚动事件
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    // 检查Y轴的距离，决定是显示还是隐藏FAB

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton childFab,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, childFab, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

        // target : (即dependency)  NestedScrollView
        // 因为此例中， rv这个child的滑动， 并不要求parent也滑动。 所以d*Unconsumer全是0,
        // 即child view全滑动了，parent不要求滑动
        System.out.println("szw consumed : x = " + dxConsumed + " ; y = " + dyConsumed);
        System.out.println("szw unconsumed : x = " + dxUnconsumed + " ; y = " + dyUnconsumed);

        if (dyConsumed > 0 && childFab.getVisibility() == View.VISIBLE) {
            childFab.hide();
        } else if (dyConsumed < 0 && childFab.getVisibility() != View.VISIBLE) {
            childFab.show();
        }
    }

}
