package cn.six.sup.rv.divider;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class HighlightGridDivider extends RecyclerView.ItemDecoration {
    private Drawable greyDrawable;
    private Drawable greenDrawable;

    public HighlightGridDivider(Drawable greyDrawable, Drawable greenDrawable) {
        this.greyDrawable = greyDrawable;
        this.greenDrawable = greenDrawable;
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }


    protected void drawHorizontal(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount(); // 110, not all the children, just the children you saw
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;


            if( (i >= 20) && (i <= 24)) {
                final int bottom = top + greenDrawable.getIntrinsicHeight();
                greenDrawable.setBounds(left, top, right, bottom);
                greenDrawable.draw(c);
            } else {
                final int bottom = top + greyDrawable.getIntrinsicHeight();
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
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + greyDrawable.getIntrinsicWidth();
            //            System.out.println("szw drawVertical() : top = "+top + " ; bottom = "+bottom);

            greyDrawable.setBounds(left, top, right, bottom);
            greyDrawable.draw(c);
        }
    }
}

