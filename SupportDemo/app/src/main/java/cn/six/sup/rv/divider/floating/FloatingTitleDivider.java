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
        fontMetrics = textPaint.getFontMetrics();
        height = (int) ctx.getResources().getDimension(R.dimen.group_title_heigth);
//        divider = ctx.getResources().getDrawable(R.drawable.divider_rv_row);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(90);
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
            c.drawRect(left, top, right, bottom, paint);
//            divider.setBounds(left, top, right, bottom);
//            divider.draw(c);

            // i不能用于这。因为rv有25项， 一屏只显示10个。那i总是0到10之间， position才是0到25之间
            // 所以getChildCount()是指页面内可见的item总数
            int position = parent.getChildAdapterPosition(child);
            if (isFirstItemInGroup(position)) {
                String title = callback.getGroup(position);
                int childTop = child.getTop();
                int titleTop = (int)(childTop - Math.abs(fontMetrics.top));
                int textHeight = (int)(Math.abs(textPaint.descent() + textPaint.ascent()) ); // 20与-80多， 成了负数，所以要abs()一下
                c.drawText(title, 0, titleTop + (height - textHeight) / 2 , textPaint);

                int titleBottom = titleTop + height;
                c.drawLine(left, titleTop, right, titleBottom, paint);
            }
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }


}
