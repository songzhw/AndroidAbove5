package cn.six.sup.rv.divider.floating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import cn.six.sup.R;

public class FloatingTitleDivider extends RecyclerView.ItemDecoration {
    private Context ctx;
    private int height;
    private Paint paint;
    private TextPaint textPaint;
    private Paint.FontMetrics fontMetrics;
    private IFloatingGroupCallback callback;
    private Drawable divider;

    public FloatingTitleDivider(Context ctx, IFloatingGroupCallback callback) {
        this.ctx = ctx;
        this.callback = callback;
        height = (int) ctx.getResources().getDimension(R.dimen.group_title_heigth);
//        divider = ctx.getResources().getDrawable(R.drawable.divider_rv_row);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(90);

        fontMetrics = textPaint.getFontMetrics();
    }

    // 这个尺寸，被计入了 RecyclerView 每个 item view 的 padding 中
    // 第二参view is the item. Here is the rlay in R.layout.item_rv
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (isFirstItemInGroup(position)) {
//            outRect.bottom = height; // bug:   这是在第0项之后加一个divider
            outRect.top = height;      // fixed: 这才能保证第0项之前有一个didiver
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

            // 两种写法
            paint.setColor(Color.BLACK);
            c.drawRect(left, top, right, bottom, paint);
//            divider.setBounds(left, top, right, bottom);
//            divider.draw(c);

            // i不能用于这。因为rv有25项， 一屏只显示10个。那i总是0到10之间， position才是0到25之间
            // 所以getChildCount()是指页面内可见的item总数
            int position = parent.getChildAdapterPosition(child);
            if (isFirstItemInGroup(position)) {
                int rectBottom = child.getTop();
                int rectTop = rectBottom - height;
                paint.setColor(Color.LTGRAY);
                c.drawRect(left, rectTop, right, rectBottom, paint);

                String title = callback.getGroup(position);
                int childTop = child.getTop();
                int titleTop = (int)(childTop - Math.abs(fontMetrics.top));
                int textHeight = (int) Math.ceil(fontMetrics.bottom - fontMetrics.top);
                c.drawText(title, 0, titleTop + (height - textHeight) / 2 , textPaint);
            } // 不用textHeight = bounds.getHeight()是因为右值只是text的高度。 (bounds由paint.getTextBounds()来)
            // 而实际绘制时， 不会只绘这么高的，还会上下有富余，用于符号，g,y等下的下部的
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }


}
