package cn.six.sup.rv.snap;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class LeftSnapHelper extends LinearSnapHelper {

    private OrientationHelper horizontalHelper;

    // 我们只考虑横向左对齐，所以只要处理out[0]的值，
    @Override
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View targetView) {
        int[] out = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            // 返回修正的偏移量
            OrientationHelper helper = getHorizontalHelper(layoutManager);
            out[0] = helper.getDecoratedStart(targetView) - helper.getStartAfterPadding();
        } else {
            out[0] = 0;
        }

        return out;
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        if (horizontalHelper == null) {
            horizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return horizontalHelper;
    }

    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper helper = getHorizontalHelper(layoutManager);
        if (layoutManager instanceof LinearLayoutManager) {
            int firstChild = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            int lastChild = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            if (firstChild == RecyclerView.NO_POSITION) {
                return null;
            }

            View child = layoutManager.findViewByPosition(firstChild);
            // 得到此时需要左对齐显示的条目
            if (helper.getDecoratedEnd(child) >= helper.getDecoratedMeasurement(child) / 2
                    && helper.getDecoratedEnd(child) > 0) {
                return child;
            } else {
                return layoutManager.findViewByPosition(firstChild + 1);
            }
        }

        return super.findSnapView(layoutManager);
    }


}