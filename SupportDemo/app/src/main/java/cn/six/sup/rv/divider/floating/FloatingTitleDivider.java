package cn.six.sup.rv.divider.floating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import cn.six.sup.R;

public class FloatingTitleDivider extends RecyclerView.ItemDecoration {
    private int height;
    private Paint paint;
    private TextPaint textPaint;
    private Paint.FontMetrics fontMetrics;
    private IFloatingGroupCallback callback;
    private int textHeight;
    private final int originalTextY, originalTextX;

    public FloatingTitleDivider(Context ctx, IFloatingGroupCallback callback) {
        this.callback = callback;
        height = (int) ctx.getResources().getDimension(R.dimen.group_title_heigth);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(90);

        fontMetrics = textPaint.getFontMetrics();
        textHeight = (int) Math.ceil(fontMetrics.bottom - fontMetrics.top);
        originalTextY = height / 2 + textHeight / 4;
        originalTextX = 50;
        AsyncTask t;
    }

    // 第二参view is the item. Here is the rlay in R.layout.item_rv
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (isFirstItemInGroup(position)) {
            outRect.top = height;
        }
        outRect.bottom = 8;
    }

    private boolean isFirstItemInGroup(int pos) {
        if (pos == 0) {
            return true;
        }
        String previousGroupText = callback.getGroup(pos - 1);
        String currentGroupText = callback.getGroup(pos);
        return !(previousGroupText.equalsIgnoreCase(currentGroupText));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int top = child.getBottom(); // 我们要在下方画分隔线，所以这个top是view的bottom
            int bottom = top + 8;
            paint.setColor(Color.BLACK);
            c.drawRect(left, top, right, bottom, paint);

            // getChildCount()是指页面内可见的item总数
            int position = parent.getChildAdapterPosition(child);
            if (isFirstItemInGroup(position)) {
                int rectBottom = child.getTop();
                int rectTop = rectBottom - height;
                paint.setColor(Color.LTGRAY);
                c.drawRect(left, rectTop, right, rectBottom, paint);

                String title = callback.getGroup(position);
                c.drawText(title, originalTextX, rectTop + originalTextY, textPaint);
            }
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        RecyclerView.LayoutManager temp = parent.getLayoutManager();
        if (!(temp instanceof LinearLayoutManager)) {
            throw new IllegalStateException("FloatingTitleDivider is noly for LinearLayoutManager!");
        }
        LinearLayoutManager layoutManager = (LinearLayoutManager) temp;
        int firstPos = layoutManager.findFirstVisibleItemPosition();

        int rectHeight = height;
        int textY = originalTextY;
        boolean isGroup = isFirstItemInGroup(firstPos + 1);
        if (isGroup) {
            View view = layoutManager.findViewByPosition(firstPos);
            int viewTop = view.getTop();  // viewTop在这时是[0, -150]范围
            rectHeight = viewTop + height;
            textY = textY + viewTop;

            if (viewTop > 0) { // 但viewTop在第0项时特殊， 值是在[150, -150]的范围，所以这里要处理一下
                rectHeight = height;
            }
        }

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        paint.setColor(Color.LTGRAY);
        c.drawRect(left, 0, right, rectHeight, paint);

        String title = callback.getGroup(firstPos);
        c.drawText(title, originalTextX, textY, textPaint);

    }


}
