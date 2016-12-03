package cn.six.sup.rv.divider;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ThreeColorGridDivider extends RecyclerView.ItemDecoration {
    private Drawable greyDrawable;
    private Drawable greenDrawable;
    private Drawable dottedDrawable;
    private int highlightStartPosition;
    private int highlightEndPosition;

    private int width;
    private int height;

    public ThreeColorGridDivider(Drawable greyDrawable, Drawable dottedDrawable, Drawable greenDrawable, int
        highlightStartPosition,
        int highlightEndPosition) {
        this.greyDrawable = greyDrawable;
        this.greenDrawable = greenDrawable;
        this.dottedDrawable = dottedDrawable;
        this.highlightStartPosition = highlightStartPosition;
        this.highlightEndPosition = highlightEndPosition;

        width = greyDrawable.getIntrinsicWidth();
        height = greyDrawable.getIntrinsicHeight();
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }


    protected void drawHorizontal(Canvas c, RecyclerView parent) {
//        final int left = parent.getPaddingLeft();
//        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount(); // not all the children, just the children you saw
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            int left = child.getLeft();
            int right = child.getRight();

            int realPosition = parent.getChildAdapterPosition(child);
            if ((realPosition >= highlightStartPosition) && (realPosition <= highlightEndPosition)) {
                final int bottom = top + greenDrawable.getIntrinsicHeight();
                greenDrawable.setBounds(left, top, right, bottom);
                greenDrawable.draw(c);
            }
            else if(realPosition == 0 || realPosition == 1 || realPosition == 5 || realPosition == 6
                    || realPosition == 10 || realPosition == 11 || realPosition == 15 || realPosition == 16) {
                final int bottom = top + height;
                dottedDrawable.setBounds(left, top, right, bottom);
                dottedDrawable.draw(c);
            } else {
                final int bottom = top + height;
                greyDrawable.setBounds(left, top, right, bottom);
                greyDrawable.draw(c);
            }
        }
    }

    protected void drawVertical(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + width;

            greyDrawable.setBounds(left, top, right, bottom);
            greyDrawable.draw(c);
        }
    }
}