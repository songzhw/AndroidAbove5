package cn.six.sup.rv.divider.floating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
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
    private int textHeight;
    private final int y;

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
        textHeight = (int) Math.ceil(fontMetrics.bottom - fontMetrics.top);
        y = height / 2 + textHeight / 4;
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
                c.drawText(title, 0, rectTop + y , textPaint);
            } // 不用textHeight = bounds.getHeight()是因为右值只是text的高度。 (bounds由paint.getTextBounds()来)
            // 而实际绘制时， 不会只绘这么高的，还会上下有富余，用于符号，g,y等下的下部的
        }

    }

    /*
    A. “被推时”，如何判断drawRect()的这个rect要画多高？ 这明显和group 2相关， 但如何相关的呢？
    B. “被推时”，如何绘制部分文字？ (group1上推， 文字也只能展示下方一小截， 这个如何做到的?)
    */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        RecyclerView.LayoutManager temp = parent.getLayoutManager();
        if(!(temp instanceof LinearLayoutManager)){
            throw new IllegalStateException("FloatingTitleDivider is noly for LinearLayoutManager!");
        }
        LinearLayoutManager layoutManager = (LinearLayoutManager) temp;
        int firstPos = layoutManager.findFirstVisibleItemPosition(); //这是all children， 不是可见范围的children
        View view = layoutManager.findViewByPosition(firstPos); // 这是指可见范围的children。 所以用firstPos大于可见范围内的数目，可能会有得到view为空
        int realTop = view.getTop() - height;
        System.out.println("szw ["+firstPos+"] getTop() = "+view.getTop() + " ; realTop = "+realTop);

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        paint.setColor(Color.LTGRAY);
        c.drawRect(left, 0, right, height, paint);

        String title = callback.getGroup(firstPos);
        c.drawText(title, 0, height/2 + textHeight/4, textPaint);

    }


}
