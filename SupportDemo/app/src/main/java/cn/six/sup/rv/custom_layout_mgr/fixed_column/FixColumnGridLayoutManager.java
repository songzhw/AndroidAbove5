package cn.six.sup.rv.custom_layout_mgr.fixed_column;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;


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
            if ( posInReal % columnSize != 0) {
                // 非每行最末一项, 就是普通平移就好
                left = offsetX;
                top = offsetY;

                offsetX += width;
            } else {
                // 每行最末一项, 以5column为例 应该是4, 9, 14, 19, ...(以0开始). 要换行.
                left = 0;
                top = offsetY;

                offsetX = width;
                offsetY += height; //TODO 这里可能有问题
            }
            System.out.println("szw ox = "+ offsetX +" ; oy = "+offsetY);
            right = left + width;
            bottom = top + height;
            layoutDecorated(view, left, top, right, bottom);
        }

    }
}

