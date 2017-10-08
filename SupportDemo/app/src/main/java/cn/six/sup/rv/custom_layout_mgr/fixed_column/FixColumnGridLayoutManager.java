package cn.six.sup.rv.custom_layout_mgr.fixed_column;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

// 问题1: 如何适用rv为wrap_content的情形 (目前还没解决)
// 问题2: 如何循环得用多viewType的各View (解决: 可以用LayMgr中的 **int getItemViewType(View view)** 方法)

public class FixColumnGridLayoutManager extends RecyclerView.LayoutManager {
    private int columnSize = 0;
    private int verticallyOffset = 0, horizontalOffset = 0;
    private int totalHeight = 0, totalWidth = 0;
    private SparseArray<Rect> itemFrames = new SparseArray<>();

    public FixColumnGridLayoutManager(int columnSize) {
        this.columnSize = columnSize;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0) return;
        if (state.isPreLayout()) return;

        detachAndScrapAttachedViews(recycler);

        int offsetX = 0, offsetY = 0;
        int itemCount = getItemCount();
        System.out.println("szw item count = "+itemCount);
        for(int i = 0; i < itemCount; i++){
            View view = recycler.getViewForPosition(i);
            measureChildWithMargins(view, 0, 0);
            addView(view);

            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);
            totalHeight += height;
            totalWidth += width;

            int posInReal = i + 1;
            int left, top, right, bottom;
            // 1. 最末一列, 布局完后要换行, 即offsetY += height, offsetX = 0
            if (posInReal % columnSize == 0){
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

            System.out.println("szw ox = "+ offsetX +" ; oy = "+offsetY);
            right = left + width;
            bottom = top + height;
            layoutDecorated(view, left, top, right, bottom);
        }

    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }


    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        offsetChildrenHorizontal(-dx);
        verticallyOffset += dx;

        // fixed first coloumn behavior
        int offsetY = 0;
        if(verticallyOffset > 0) {
            int itemCount = getItemCount();
            for (int i = 0; i < itemCount; i++) {
                int posInReal = i + 1;
                // 第一列
                if( posInReal % columnSize == 1){
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
        return dx;
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


