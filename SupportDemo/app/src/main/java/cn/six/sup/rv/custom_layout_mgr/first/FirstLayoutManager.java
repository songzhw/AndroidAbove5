package cn.six.sup.rv.custom_layout_mgr.first;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

class FirstLayoutManager extends RecyclerView.LayoutManager {
    private int verticalScrollOffset = 0;
    private int totalHeight = 0;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler); //1. rv的两级缓存

        int offsetY = 0;
        int itemCount = getItemCount();
        for (int i = 0; i < itemCount; i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view, 0, 0);

            // 2. 这的宽高, 都是都是包含了divider的宽高的(divider, 即ItemDecorate)
            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);

            layoutDecorated(view, 0, offsetY, width, offsetY + height);
            offsetY += height;
        }
    }


    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int travel = dy; //实际要滑动的距离
        int verticalSpace = getVerticalSpace();

        // 如果滑到了最顶部
        if (verticalScrollOffset + dy < 0) {
            travel = -verticalScrollOffset;
        }
        // 如果滑到最底部
        else if (verticalScrollOffset + dy > (totalHeight - verticalSpace)) {
            travel = totalHeight - verticalSpace - verticalScrollOffset;
        }

        verticalScrollOffset += travel;
        offsetChildrenVertical(-travel);
        return travel;
    }

    // 获取RecyclerView在垂直方向上的可用空间，即去除了padding后的高度
    private int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }


}
