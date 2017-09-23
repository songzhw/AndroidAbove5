package cn.six.sup.rv.custom_layout_mgr.hex;

import android.graphics.Rect;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


public class HexLayoutManager extends RecyclerView.LayoutManager {
    private static final int SIZE_PER_ROW = 2;
    private static final int DEFAULT_GROUP_INTERVAL = 10; //每组之间的间隙(正六边形之间的)(横向)
    private float horizonGap = DEFAULT_GROUP_INTERVAL; //代表横向的间距,只三个正六边形形成的等边三角形的中心距离 (存在默认值)
    private float verticalGap; //代表纵向的间隔,指两个正六边形之间的上下间距
    private int centerOffset;  //居中的偏移量
    private int totalHeight = 0, verticalOffset = 0;

    private SparseArrayCompat<Rect> pool = new SparseArrayCompat<>();


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
        int radius = Math.min(width, height) / 2;

        verticalGap = (horizonGap / MathUtil.sin(60)) - 2 * (radius - radius * MathUtil.sin(60));
        int rowWidth = (int) (0.75 * width + width - horizonGap);  //每组的最大宽度 第一排的宽度加上第二排的宽度
        if (rowWidth < getHorizontalSpace()) {
            centerOffset = (getHorizontalSpace() - rowWidth) / 2;
        } else {
            centerOffset = 0;
        }

        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view, 0, 0);

            Rect frame = pool.get(i);
            if(frame == null){
                frame = new Rect();
                pool.put(i, frame);
            }

            int offsetHeight = (int) ((i / SIZE_PER_ROW) * (height + verticalGap));
            int left, top, right, bottom;
            if (isItemInFirstLine(i)) {  //每组的第一行
                left = centerOffset;
                top = offsetHeight;
                right = centerOffset + width;
                bottom = height + offsetHeight;
            } else {
                //X轴的偏移是从 正六边形的外圆 3/2 R出开始偏移
                int itemOffsetWidth = (int) ((3f / 2f) * radius + horizonGap);
                //Y轴的第一次偏移是 取 (2个正六边形的宽度 + 中间间距) 得到当前第二排正六边形的中点 然后往回减 得到的.
                int itemOffsetHeight = (int) ((int) ((2 * width + verticalGap) / 2) - 0.5 * width);
                left = centerOffset + itemOffsetWidth;
                top = itemOffsetHeight + offsetHeight;
                right = centerOffset + itemOffsetWidth + width;
                bottom = offsetHeight + itemOffsetHeight + height;
            }
            frame.set(left, top, right, bottom);
        }

        int columnNum = getColumnSize();
        int totalHeight = (int) (columnNum * height + (columnNum - 1) * verticalGap);
        int itemOffsetHeight = (int) ((int) ((2 * width + verticalGap) / 2) - 0.5 * width);
        if (!isItemInFirstLine(getItemCount() - 1)) {
            totalHeight += itemOffsetHeight;
        }
        this.totalHeight = Math.max(totalHeight, getVerticalSpace());

        recycleAndFillChildren(recycler, state);
    }

    private void recycleAndFillChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0 || state.isPreLayout()) {
            return;
        }

        Rect displayRect = new Rect(0, verticalOffset,
                getHorizontalSpace(),
                getVerticalSpace() + verticalOffset);

        // put invisible item to the pool
        Rect childFrame = new Rect();
        int childCount = getChildCount();
        System.out.println("szw 1 : "+childCount);
        for (int i = 0; i < getChildCount(); i++) {
            System.out.println("szw 2 : "+getChildCount());
            View child = getChildAt(i);
            childFrame.left = getDecoratedLeft(child);
            childFrame.top = getDecoratedTop(child);
            childFrame.right = getDecoratedRight(child);
            childFrame.bottom = getDecoratedBottom(child);
            // 如果Item没有在显示区域，就说明需要回收
            if (!Rect.intersects(displayRect, childFrame)) {
                this.removeAndRecycleView(child, recycler); // LayoutManager的方法
            }
        }

        // reuse the items in the pool
        for (int i = 0; i < getItemCount(); i++) {
            Rect frame = pool.get(i);
            if (Rect.intersects(displayRect, frame)) {
                View scrap = recycler.getViewForPosition(i);
                addView(scrap);
                measureChildWithMargins(scrap, 0, 0);
                layoutDecorated(scrap, frame.left, frame.top - verticalOffset,
                        frame.right, frame.bottom - verticalOffset);
            }
        }
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public boolean canScrollHorizontally() {
        return false;
    }

    @Override // 想看底部, dy就是+.  否则为-.
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);

        if(verticalOffset + dy < 0){
            dy = -verticalOffset;
        } else if(verticalOffset + dy > (totalHeight - getVerticalSpace())) {
            dy = totalHeight - getVerticalSpace() - verticalOffset;
        }
        verticalOffset += dy;

        offsetChildrenVertical(-dy);

        recycleAndFillChildren(recycler, state);
        System.out.println("szw scroll() : "+getChildCount());
        return dy;
    }

    // 判断当前索引位置 是否处于组内的第一行
    private boolean isItemInFirstLine(int index) {
        return index % 2 == 0; //定制为2格的正六边形
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    private int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    private int getColumnSize() {
        return (int) Math.ceil(getItemCount() / (float) SIZE_PER_ROW); //向上取整
    }


    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
