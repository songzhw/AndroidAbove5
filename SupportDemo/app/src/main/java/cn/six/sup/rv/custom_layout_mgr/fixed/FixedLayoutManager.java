package cn.six.sup.rv.custom_layout_mgr.fixed;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

class FixedLayoutManager extends RecyclerView.LayoutManager {
    private int verticalScrollOffset = 0; // 竖直方向的滑动偏移量
    private int totalHeight = 0;
    private SparseArray<Rect> allItemFrames = new SparseArray<>();


    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0) {
            return;
        }
        if (state.isPreLayout()) {
            return;
        }  // 跳过preLayout，preLayout主要用于支持动画

        detachAndScrapAttachedViews(recycler); // rv的两级缓存. 现在只是全打回了回收池里

        int offsetY = 0;
        int itemCount = getItemCount();
        for (int i = 0; i < itemCount; i++) {
            View view = recycler.getViewForPosition(i);
            addView(view); // 只是add, 并没有layout. 若不加layoutDecorated(), 那显示就是一片空白
            measureChildWithMargins(view, 0, 0);

            // 这的宽高, 都是都是包含了divider的宽高的(divider, 即ItemDecorate)
            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);
            totalHeight += height;

            Rect frame = allItemFrames.get(i);
            if (frame == null) {
                frame = new Rect();
            }
            frame.set(0, offsetY, width, offsetY + height);
            System.out.println("szw CF : frame = "+frame);
            allItemFrames.put(i, frame);  // replacing the previous mapping from the specified key if there was one.
            offsetY += height;
        }

        //如果所有子View的高度和没有填满RecyclerView的高度,  则将高度设置为RecyclerView的高度
        totalHeight = Math.max(totalHeight, getVerticalSpace());
        // 在Nexus6p(2560*1440)手机上, rv是match_parent的, 数据超过了一屏. 这时totalHeight = 3744, getVerticalSpace = 2308
        // 若数据没一屏, 但rv是match_parent的, 这时totalHeight = 864, getVerticalSpace = 2308

        recycleAndFillItems(recycler, state);
    }


    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        // 先要detach所有attached的view. 因为滑动就有变化, 就有新的项入回收池, 也要从回收池拿出数据来填充
        detachAndScrapAttachedViews(recycler);

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

        // 前面是detach, 这里要回收/循环利用子项了
        recycleAndFillItems(recycler, state);
        return travel;
    }


    private void recycleAndFillItems(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout()) { // 跳过preLayout，preLayout主要用于支持动画
            return;
        }

        // 当前scroll offset状态下, 整个rv的显示区域
        Rect displayFrame = new Rect(0, verticalScrollOffset, getHorizontalSpace(), verticalScrollOffset + getVerticalSpace());

        // 将滑出屏幕的Items回收到Recycle缓存中
        Rect childFrame = new Rect();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            childFrame.left = getDecoratedLeft(child);
            childFrame.top = getDecoratedTop(child);
            childFrame.right = getDecoratedRight(child);
            childFrame.bottom = getDecoratedBottom(child);
            // 如果Item没有在显示区域，就说明需要回收
            if (!Rect.intersects(displayFrame, childFrame)) {
                this.removeAndRecycleView(child, recycler); // LayoutManager的方法. 会导致getChildCount()变得更少
            }
        }

        // 从缓存中拿出来复用
        int itemCount = getItemCount(); //itemCount == adapter.getItemCount()
        for (int i = 0; i < itemCount; i++) {
            if (Rect.intersects(displayFrame, allItemFrames.get(i))) {
                View scrap = recycler.getViewForPosition(i);
                measureChildWithMargins(scrap, 0, 0);
                addView(scrap);

                Rect frame = allItemFrames.get(i);
                //将这个item布局出来
                layoutDecorated(scrap,
                        frame.left,
                        frame.top - verticalScrollOffset,
                        frame.right,
                        frame.bottom - verticalScrollOffset);
            }
        }

        // is not in the top
        if (verticalScrollOffset > 0) {
            View scrap = recycler.getViewForPosition(0);
            measureChildWithMargins(scrap, 0, 0);
            addView(scrap);

            Rect frame = allItemFrames.get(0);
            System.out.println("szw frame0 = "+frame+" ; view = "+scrap);
            layoutDecorated(scrap, 0, 0, frame.right, frame.bottom);
        }


    }


    // 获取RecyclerView在垂直方向上的可用空间，即去除了padding后的高度
    private int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop(); //此三参数, 皆是来自layoutManager
    }

    private int getHorizontalSpace() {
        return getWidth() - getPaddingRight() - getPaddingLeft();
    }


}
