package cn.six.sup.rv.divider;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

@Deprecated
// [!!!] This class will crash our project
public class GridDivider01 extends RecyclerView.ItemDecoration {

    private Drawable horizontalDivider;
    private Drawable verticalDivider;
    private int columnCount;

    public GridDivider01(Drawable horizontalDivider, Drawable verticalDivider, int numColumns) {
        this.horizontalDivider = horizontalDivider;
        this.verticalDivider = verticalDivider;
        columnCount = numColumns;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        boolean childIsInLeftmostColumn = (parent.getChildAdapterPosition(view) % columnCount) == 0;
        if (!childIsInLeftmostColumn) {
            outRect.left = horizontalDivider.getIntrinsicWidth();
        }

        boolean childIsInFirstRow = (parent.getChildAdapterPosition(view)) < columnCount;
        if (!childIsInFirstRow) {
            outRect.top = verticalDivider.getIntrinsicHeight();
        }
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        drawHorizontalDividers(canvas, parent);
        drawVerticalDividers(canvas, parent);
    }

    private void drawHorizontalDividers(Canvas canvas, RecyclerView parent) {
        int childCount = parent.getChildCount();
        int rowCount = childCount / columnCount;
        int lastRowChildCount = childCount % columnCount;

        for (int i = 1; i < columnCount; i++) {
            int lastRowChildIndex;
            if (i < lastRowChildCount) {
                lastRowChildIndex = i + (rowCount * columnCount);
            } else {
                lastRowChildIndex = i + ((rowCount - 1) * columnCount);
            }

            View firstRowChild = parent.getChildAt(i);
            View lastRowChild = parent.getChildAt(lastRowChildIndex);

            int dividerTop = firstRowChild.getTop();
            int dividerRight = firstRowChild.getLeft();
            int dividerLeft = dividerRight - horizontalDivider.getIntrinsicWidth();
            int dividerBottom = lastRowChild.getBottom();

            horizontalDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
            horizontalDivider.draw(canvas);
        }
    }

    private void drawVerticalDividers(Canvas canvas, RecyclerView parent) {
        int childCount = parent.getChildCount();
        int rowCount = childCount / columnCount;
        System.out.println("szw childCount = " + childCount + " ; rowCount = " + rowCount);
        int rightmostChildIndex;
        for (int i = 1; i <= rowCount; i++) {
            if (i == rowCount) {
                rightmostChildIndex = parent.getChildCount() - 1;
            } else {
                rightmostChildIndex = (i * columnCount) + columnCount - 1;
            }

            System.out.println("szw i = " + i);
            View leftmostChild = parent.getChildAt(i * columnCount);
            View rightmostChild = parent.getChildAt(rightmostChildIndex);

            int dividerLeft = leftmostChild.getLeft();  // [!!!] Crash!, NPE
            int dividerBottom = leftmostChild.getTop();
            int dividerTop = dividerBottom - verticalDivider.getIntrinsicHeight();
            int dividerRight = rightmostChild.getRight();

            verticalDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
            verticalDivider.draw(canvas);
        }
    }
}
