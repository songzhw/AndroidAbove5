package cn.six.sup.rv.snap;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/* 只能和LinearLayoutManager合作. */
public class LeftSnapHelper extends LinearSnapHelper {

    private OrientationHelper horizontalHelper;

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        if (horizontalHelper == null) {
            horizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return horizontalHelper;
    }

    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        horizontalHelper = getHorizontalHelper(layoutManager);
        if (layoutManager instanceof LinearLayoutManager) {
            int firstChildPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            if (firstChildPosition == RecyclerView.NO_POSITION) {
                return null;
            }

            View child = layoutManager.findViewByPosition(firstChildPosition);
            int childWidth = horizontalHelper.getDecoratedMeasurement(child); //因为现在是水平方向, 所以此方法得出来的是水平上的所占空间, 自然也就是width了
            int childEnd = horizontalHelper.getDecoratedEnd(child);

            if (childEnd >= childWidth / 2) {
                return child;
            } else {
                return layoutManager.findViewByPosition(firstChildPosition + 1); //若是往右滑, 过半, 那这时firstVisibleItem的childEnd就很小, 没过半, 所以我们要跳到下一项去, 所以这里 +1
            }
        }

        return super.findSnapView(layoutManager);
    }

    // 我们只考虑横向左对齐，所以只要处理out[0]的值，即x轴上的值
    @Override
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View targetView) {
        int[] out = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            // 返回修正的偏移量
            horizontalHelper = getHorizontalHelper(layoutManager);
            out[0] = horizontalHelper.getDecoratedStart(targetView) - horizontalHelper.getStartAfterPadding();
        } else {
            out[0] = 0;
        }

        return out;
    }



}
/*
SnapHelper的源码:
void snapToTargetExistingView() {
    LayoutManager layoutManager = mRecyclerView.getLayoutManager();

    View snapView = findSnapView(layoutManager);

    int[] snapDistance = calculateDistanceToFinalSnap(layoutManager, snapView);
    if (snapDistance[0] != 0 || snapDistance[1] != 0) {
        mRecyclerView.smoothScrollBy(snapDistance[0], snapDistance[1]);
    }
}
所以我们需要findSnapView(), calculateDistanceToFinalSnap()

 */

/*
android.support.v7.widget.OrientationHelper

It is developed to easily support vertical and horizontal orientations in a LayoutManager
but can also be used to abstract calls around view bounds and child measurements with margins and decorations.

getDecoratedMeasurement(view): Returns the space occupied by this View in the current orientation including decorations and margins.
getDecoratedStart(view): Returns the start of the view including its decoration and margin.
getDecoratedEnd(view):  Returns the end of the view including its decoration and margin.
getStartAfterPadding(): Returns the start position of the layout after the start padding is added.

以rv是水平, 且一item宽度为1050为例, 那:
 decoratedMeasurement一定总是1050
 decroatedStart一开始是负数, 一直到0
 decoratedEnd最终是1050
 但不论decroatedStart, decoratedEnd如何在滑动时变化, (decoratedEnd - decoratedStart)的值一定是1050,

如滑动时, 记录[position] decoratedStart - decoratedEnd ; decoratedMeasurement
    szw findSnapView(): [0] -661 - 389 ; m = 1050
    szw findSnapView(): [0] -807 - 243 ; m = 1050
    szw findSnapView(): [1] 0 - 1050 ; m = 1050

所以, decoratedEnd >= decoratedMeasurement / 2时, 这说明, 已经触发了SnapHelper的条件, 松手就会自动滑动到***项了.
*/

/*
findSnapView()在滑动时不会被调用
只有在松手后, 才会被调用 -- 用来看下要滑动到哪个位置.

如从第5项到第4项, 滑动过半, 松手, 日志为:
    szw findSnapView(): [4] -381 - 669 ; m = 1050
    szw findSnapView(): [4] -392 - 658 ; m = 1050
    szw findSnapView(): [4] 0 - 1050 ; m = 1050

 */