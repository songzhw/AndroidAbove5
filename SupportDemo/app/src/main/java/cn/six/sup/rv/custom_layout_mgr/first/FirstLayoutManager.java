package cn.six.sup.rv.custom_layout_mgr.first;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

class FirstLayoutManager extends RecyclerView.LayoutManager {
    private int verticalScrollOffset = 0; // 竖直方向的滑动偏移量
    private int totalHeight = 0;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0) return;

        // 跳过preLayout，preLayout主要用于支持动画
        if (state.isPreLayout()) {
            return;
        }

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
            totalHeight += height;
        }

        //如果所有子View的高度和没有填满RecyclerView的高度,  则将高度设置为RecyclerView的高度
        totalHeight = Math.max(totalHeight, getVerticalSpace());
        // 在Nexus6p(2560*1440)手机上, rv是match_parent的, 数据超过了一屏. 这时totalHeight = 3744, getVerticalSpace = 2308
        // 若数据没一屏, 但rv是match_parent的, 这时totalHeight = 864, getVerticalSpace = 2308
    }


    @Override
    public boolean canScrollVertically() {
        return true;
    }

    // 1. 看rv下面的内容, 即往上拉, dy就是正值; 否则dy就是负值. (dy相当于move, 每move一次就有一次dy值.)
    // 注意, 到底后,再往上拉, dy仍是有的, 仍是正值. 值的大小看你的拉动的距离有多大.
    // 2. 到底部: rv是match_parent的, 数据超过了一屏. 这时totalHeight = 3744, getVerticalSpace = 2308, 二值差为1436
    // 这时的verticalScrollOffset最大的值, 就是这个1436
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int travel = dy; //实际要滑动的距离
        int verticalSpace = getVerticalSpace();
        System.out.println(" szw scrollVerticallyBy() : verticalScrollOffset = "+verticalScrollOffset+ " ; dy = "+dy);

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
        return getHeight() - getPaddingBottom() - getPaddingTop(); //此三参数, 皆是来自rv
    }

}
