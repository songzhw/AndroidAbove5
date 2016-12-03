package cn.six.sup.rv.option_chain.demo2;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * This LayoutManager Class is needed when a Grid-like RecyclerView is a child of another RecycleView.
 * It properly lays out the inner RecyclerView otherwise it will not be visible in the parent RecyclerView.
 */

public class NestedGridLayoutManager extends GridLayoutManager {
    private int[] measuredDimension = new int[2];

    public NestedGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public NestedGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int widthSize = View.MeasureSpec.getSize(widthSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);

        int width = 0;
        int height = 0;
        int count = getItemCount();
        int span = getSpanCount();
        for (int index = 0; index < count; index++) {
            measureScrapChild(recycler, index,
                    View.MeasureSpec.makeMeasureSpec(index, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(index, View.MeasureSpec.UNSPECIFIED),
                    measuredDimension);

            // add height row by row
            width = measuredDimension[0];
            if (getOrientation() != HORIZONTAL) {
                if (index % span == 0) {
                    height = height + measuredDimension[1];
                }
            }
        }

        switch (widthMode) {
            case View.MeasureSpec.EXACTLY:
                width = widthSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }

        switch (heightMode) {
            case View.MeasureSpec.EXACTLY:
                height = heightSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }

        setMeasuredDimension(width, height);
    }

    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec,
                                   int heightSpec, int[] measuredDimension) {
        if (position < getItemCount()) {
            try {
                View view = recycler.getViewForPosition(0);
                if (view != null) {
                    RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
                    int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
                            getPaddingLeft() + getPaddingRight(), p.width);
                    int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
                            getPaddingTop() + getPaddingBottom(), p.height);
                    view.measure(childWidthSpec, childHeightSpec);
                    measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;
                    measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin + p.topMargin;
                    recycler.recycleView(view);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}