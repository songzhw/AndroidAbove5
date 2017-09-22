package cn.six.sup.rv.custom_layout_mgr.hex;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


public class HexLayoutManager extends RecyclerView.LayoutManager {

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        // initChildren
        int itemCount = getItemCount();
        if (itemCount < 0) {
            return;
        }
        if (state.isPreLayout()) {
            return;
        }
        detachAndScrapAttachedViews(recycler);

        measureChildrens(recycler, state);

        recycleAndFillChildren(recycler, state);
    }


    private void measureChildrens(RecyclerView.Recycler recycler, RecyclerView.State state) {
        // 取到宽高. 这些宽高是包含了divider的
        View tmp = recycler.getViewForPosition(0);
        int width = getDecoratedMeasuredWidth(tmp);
        int height = getDecoratedMeasuredHeight(tmp);
        int radius = Math.min(width, height) / 2;

        float gap = (10 / MathUtil.sin(60)) - 2 * (radius - radius * MathUtil.sin(60));
        int groupWidth = (int)(0.75 * width + width - 10);
        int gravityOffset;

        if (groupWidth < getHorizontalSpace()) {
            gravityOffset = (getHorizontalSpace() - groupWidth) / 2;
        } else {
            gravityOffset = 0;
        }

        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            int offsetHeight = (int) ((i / 2) * (height + gap));
            //每组的第一行
            if (isItemInFirstLine(i)) {
                int left = gravityOffset;
                int top = offsetHeight;
                int right = gravityOffset + width;
                int bottom = height + offsetHeight;
                layoutDecorated(view, left, top, right, bottom);
            } else {
                //X轴的偏移是从 正六边形的外圆 3/2 R出开始偏移
                int itemOffsetWidth = (int) ((3f / 2f) * radius + 10);
                //Y轴的第一次偏移是 取 (2个正六边形的宽度 + 中间间距) 得到当前第二排正六边形的中点 然后往回减 得到的.
                int itemOffsetHeight = (int) ((int) ((2 * width + gap) / 2) - 0.5 * width);
                int left = gravityOffset + itemOffsetWidth;
                int top = itemOffsetHeight + offsetHeight;
                int right = gravityOffset + itemOffsetWidth + width;
                int bottom = offsetHeight + itemOffsetHeight + height;
                layoutDecorated(view, left, top, right, bottom);
            }
        }



    }

    private void recycleAndFillChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
    }



    private boolean isItemInFirstLine(int index) {
        //定制为2格的正六边形
        return index % 2 == 0;
    }


    private int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
