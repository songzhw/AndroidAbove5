package cn.six.sup.rv.custom_layout_mgr.fixed_column;

import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;


// 问题1: 如何适用rv为wrap_content的情形 (目前还没解决)
// 问题2: 如何循环得用多viewType的各View (解决: 可以用LayMgr中的 **int getItemViewType(View view)** 方法)

public class FixColumnGridLayoutManager extends RecyclerView.LayoutManager {
    private int columnSize = 0;
    private int verticalOffset = 0, horizontalOffset = 0;
    private int totalHeight = 0, totalWidth = 0;
    private SparseArray<SparseArray<Rect>> cache = new SparseArray<>();

    private void diagnoseCache() {
        System.out.println("szw2: cache size = " + cache.size());
//        for (int i = 0; i < cache.size(); i++) {
//            int key = cache.keyAt(i);
//            SparseArray<Rect> item = cache.get(key);
//            System.out.println("szw2 :      [sub " + key + "] " + " : " + item.size());
//        }
    }


    public FixColumnGridLayoutManager(int columnSize) {
        this.columnSize = columnSize;
    }

    // 又没有onDestory(), 虽说有onDetachWindow(), 但不是我心中所要. 所以不用HandlerThread方案

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
        }

        detachAndScrapAttachedViews(recycler);

        int offsetX = 0, offsetY = 0;
        int itemCount = getItemCount();
        System.out.println("szw onLayoutChildren() item count = " + itemCount);
        for (int i = 0; i < itemCount; i++) {
            View view = recycler.getViewForPosition(i);
            measureChildWithMargins(view, 0, 0);
            addView(view);

            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);
            totalHeight += height;
            totalWidth += width;

            int type = getItemViewType(view);
            SparseArray<Rect> typeCache = cache.get(type);
            if (typeCache == null) {
                typeCache = new SparseArray<>();
            }
            Rect frame = typeCache.get(i);
            if (frame == null) {
                frame = new Rect();
            }

            int posInReal = i + 1;
            int left, top, right, bottom;
            // 1. 最末一列, 布局完后要换行, 即offsetY += height, offsetX = 0
            if (posInReal % columnSize == 0) {
                left = offsetX;
                top = offsetY;

                offsetX = 0;
                offsetY += height; //TODO 这里可能有问题. 因为这个height只是一项的高度,不是一行的高度
            }
            // 2. 中间的普通列, 按offsetX, offsetY来就好了
            else {
                left = offsetX;
                top = offsetY;
                offsetX += width;
            }

            right = left + width;
            bottom = top + height;
//            layoutDecorated(view, left, top, right, bottom);
            frame.set(left, top, right, bottom);
            typeCache.put(i, frame);
            cache.put(type, typeCache);
        }

        recycleAndFill(recycler);

    }

    private void recycleAndFill(final RecyclerView.Recycler recycler) {
//        System.out.println("szw recycleAndFill()");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
                // 当前scroll offset状态下, 整个rv的显示区域
                Rect displayFrame = new Rect(horizontalOffset, 0, horizontalOffset + getHorizontalSpace(), getVerticalSpace());

                // 将滑出屏幕的Items回收到Recycle缓存中
                Rect childFrame = new Rect();
                for (int i = 0; i < getChildCount(); i++) {
                    final View child = getChildAt(i);
                    childFrame.left = getDecoratedLeft(child);
                    childFrame.top = getDecoratedTop(child);
                    childFrame.right = getDecoratedRight(child);
                    childFrame.bottom = getDecoratedBottom(child);
                    // 如果Item没有在显示区域，就说明需要回收
                    if (!Rect.intersects(displayFrame, childFrame)) {
                        // TODO 1. this post(runnable) cause the onLayoutChildren() get called infinitely
//                        child.post(new Runnable() {
//                            @Override
//                            public void run() {
                                removeAndRecycleView(child, recycler); // LayoutManager的方法. 会导致getChildCount()变得更少
//                            }
//                        });
                    }
                }

                // 从缓存中拿出来复用
                int itemCount = getItemCount(); //itemCount == adapter.getItemCount()
                for (int i = 0; i < itemCount; i++) {
                    View view = recycler.getViewForPosition(i);
                    int type = getItemViewType(view);
                    final Rect viewFrame = cache.get(type).get(i);

                    if (Rect.intersects(displayFrame, viewFrame)) {
                        final View scrap = recycler.getViewForPosition(i);

                        // TODO 2. this post(runnable) cause a total white screen
//                        scrap.post(new Runnable() {
//                            @Override
//                            public void run() {
                                measureChildWithMargins(scrap, 0, 0);
                                addView(scrap);

                                //将这个item布局出来
                                layoutDecorated(scrap,
                                        viewFrame.left - horizontalOffset,
                                        viewFrame.top,
                                        viewFrame.right - horizontalOffset,
                                        viewFrame.bottom);
//                            }
//                        });

                    }
                }

/*
        // fixed first coloumn behavior
        int offsetY = 0;
        if (horizontalOffset > 0) {
            for (int i = 0; i < itemCount; i++) {
                int posInReal = i + 1;
                // 第一列
                if (posInReal % columnSize == 1) {
                    View view = recycler.getViewForPosition(i);
                    measureChildWithMargins(view, 0, 0);
                    addView(view);

                    int width = getDecoratedMeasuredWidth(view);
                    int height = getDecoratedMeasuredHeight(view);

                    layoutDecorated(view, 0, offsetY, width, offsetY + height);
                    offsetY += height;
                }
            }
        }
*/

                diagnoseCache();
//            }
//        }).start();

    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }


    @Override
    public int scrollHorizontallyBy(final int dx, final RecyclerView.Recycler recycler, final RecyclerView.State state) {
//
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 先要detach所有attached的view. 因为滑动就有变化, 就有新的项入回收池, 也要从回收池拿出数据来填充
                detachAndScrapAttachedViews(recycler);

                offsetChildrenHorizontal(-dx);
                horizontalOffset += dx;

                if (!state.isPreLayout()){
                    recycleAndFill(recycler);
                }
            }
        }).start();

        return dx;
    }

    // 获取RecyclerView在垂直方向上的可用空间，即去除了padding后的高度
    private int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop(); //此三参数, 皆是来自layoutManager
    }

    private int getHorizontalSpace() {
        return getWidth() - getPaddingRight() - getPaddingLeft();
    }

    // ================= 先disable掉, 方便我调试 =================
    //    @Override
    //    public boolean canScrollVertically() {
    //        return true;
    //    }

    //    @Override
    //    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
    //        offsetChildrenVertical(-dy);
    //        horizontalOffset += dy;
    //        return dy;
    //    }
}
