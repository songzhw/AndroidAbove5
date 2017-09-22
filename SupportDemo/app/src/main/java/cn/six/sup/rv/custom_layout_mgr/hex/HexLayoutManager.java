package cn.six.sup.rv.custom_layout_mgr.hex;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


public class HexLayoutManager extends RecyclerView.LayoutManager {
    private static final int GROUP_SIZE = 2;
    private static final int DEFAULT_GROUP_INTERVAL = 10; //每组之间的间隙(正六边形之间的)(横向)
    private float horizonGap = DEFAULT_GROUP_INTERVAL; //代表横向的间距,只三个正六边形形成的等边三角形的中心距离 (存在默认值)
    private float verticalGap; //代表纵向的间隔,指两个正六边形之间的上下间距
    private int centerOffset;  //居中的偏移量
    private int totalHeight, verticalOffset;


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0 || state.isPreLayout()) {
            return;
        }
        detachAndScrapAttachedViews(recycler);
        measureChildren(recycler, state);
        recycleAndFillChildren(recycler, state);
    }

    private void measureChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        View temp = recycler.getViewForPosition(0);
        measureChildWithMargins(temp, 0, 0);
        int width = getDecoratedMeasuredWidth(temp);
        int height = getDecoratedMeasuredHeight(temp);
        int radius = width / 2;

        verticalGap = (horizonGap / MathUtil.sin(60)) - 2 * (radius - radius * MathUtil.sin(60));
        int rowWidth = (int) (0.75 * width + width - horizonGap);  //每组的最大宽度 第一排的宽度加上第二排的宽度

        if (rowWidth < getHorizontalSpace()) {
            centerOffset = (getHorizontalSpace() - rowWidth) / 2;
        } else {
            centerOffset = 0;
        }

        for (int i = 0; i < getItemCount(); i++) {
            View scrap = recycler.getViewForPosition(i);
            addView(scrap);
            measureChildWithMargins(scrap, 0, 0);

            int offsetHeight = (int) ((i / GROUP_SIZE) * (height + verticalGap));
            if (isItemInFirstLine(i)) {  //每组的第一行
                int left = centerOffset;
                int top = offsetHeight;
                int right = centerOffset + width;
                int bottom = height + offsetHeight;
                layoutDecorated(scrap, left, top, right, bottom);
            } else {
                //X轴的偏移是从 正六边形的外圆 3/2 R出开始偏移
                int itemOffsetWidth = (int) ((3f / 2f) * radius + horizonGap);
                //Y轴的第一次偏移是 取 (2个正六边形的宽度 + 中间间距) 得到当前第二排正六边形的中点 然后往回减 得到的.
                int itemOffsetHeight = (int) ((int) ((2 * width + verticalGap) / 2) - 0.5 * width);
                int left = centerOffset + itemOffsetWidth;
                int top = itemOffsetHeight + offsetHeight;
                int right = centerOffset + itemOffsetWidth + width;
                int bottom = offsetHeight + itemOffsetHeight + height;
                layoutDecorated(scrap, left, top, right, bottom);
            }
        }
    }

    private void recycleAndFillChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
    }

    // 判断当前索引位置 是否处于组内的第一行
    private boolean isItemInFirstLine(int index) {
        return index % 2 == 0; //定制为2格的正六边形
    }

    private int getGroupSize() {
        return (int) Math.ceil(getItemCount() / (float) GROUP_SIZE); //向上取整
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
