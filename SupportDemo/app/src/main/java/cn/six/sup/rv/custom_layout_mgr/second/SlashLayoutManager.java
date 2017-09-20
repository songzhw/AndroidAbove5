package cn.six.sup.rv.custom_layout_mgr.second;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by songzhw on 2016-11-12
 */

public class SlashLayoutManager extends RecyclerView.LayoutManager{

    @Override // from LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler); // 分离所有的itemView

        int offsetX = 0;
        int offsetY = 0;

        for (int i = 0; i < getItemCount(); i++) {
            // 根据position获取一个碎片view，可以从回收的view中获取，也可能新构造一个
            View scrap = recycler.getViewForPosition(i);

            addView(scrap);
            measureChildWithMargins(scrap, 0, 0);  // 计算此碎片view包含边距的width, height

            int width = getDecoratedMeasuredWidth(scrap);  // 获取此碎片view包含边距和装饰的宽度width
            int height = getDecoratedMeasuredHeight(scrap); // 获取此碎片view包含边距和装饰的高度height
            // Important！布局到RecyclerView容器中，所有的计算都是为了得出任意position的item的边界来布局
            layoutDecorated(scrap, offsetX, offsetY, offsetX + width, offsetY + height);

            offsetX += width;
            offsetY += height;
        }
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        offsetChildrenHorizontal(-dx);
        return dx;
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        offsetChildrenVertical(-dy);
        return dy;
    }
}
