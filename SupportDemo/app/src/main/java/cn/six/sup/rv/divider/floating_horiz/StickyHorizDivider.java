package cn.six.sup.rv.divider.floating_horiz;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import cn.six.sup.R;
import cn.six.sup.rv.divider.floating.IFloatingGroupCallback;

public class StickyHorizDivider extends RecyclerView.ItemDecoration {
    public static final int SPACE = 8;
    private final int originalTextY, originalTextX;
    private int width;
    private Paint paint;
    private TextPaint textPaint;
    private Paint.FontMetrics fontMetrics;
    private IFloatingGroupCallback callback;
    private int textHeight;

    public StickyHorizDivider(Context ctx, IFloatingGroupCallback callback) {
        this.callback = callback;
        width = (int) ctx.getResources().getDimension(R.dimen.group_title_width);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(90);

        fontMetrics = textPaint.getFontMetrics();
        textHeight = (int) Math.ceil(fontMetrics.bottom - fontMetrics.top);
        originalTextY = width / 2 + textHeight / 4;
        originalTextX = 50;
        AsyncTask t;
    }

    // 第二参view is the item. Here is the rlay in R.layout.item_rv_horizontal
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (isFirstItemInGroup(position)) {
            outRect.left = width; //这就是我们的sticky header要呆的地方
        }
        outRect.right = SPACE; // 给普通内容的每item之间也加一个间隔
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
        int top = parent.getPaddingTop();
        int bottom = parent.getPaddingBottom();
        for (int i = 0; i < childCount; i++) {
            // 画每个item都有的分隔线
            View child = parent.getChildAt(i);
            int left = child.getLeft();
            int right = left + SPACE;
            paint.setColor(Color.BLACK);
            c.drawRect(left, top, right, bottom, paint);

            // getChildCount()是指页面内可见的item总数
            // 画sticky header
            int position = parent.getChildAdapterPosition(child);
            if (isFirstItemInGroup(position)) {
                int headerRight = child.getLeft();
                int headerLeft = headerRight - width;
                paint.setColor(Color.LTGRAY);
                c.drawRect(headerLeft, top, headerRight, bottom, paint);

                String title = callback.getGroup(position);
                c.drawText(title, originalTextX, 100, textPaint);
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

        // 代码和FloatingTitleDemo类似， 就不详细写了。
        //        int rectWidth = width;//        int textY = originalTextY;//        boolean isGroup = isFirstItemInGroup(firstPos + 1);//        if (isGroup) {//            View view = layoutManager.findViewByPosition(firstPos);//            int viewLeft = view.getLeft(); // viewTopLeft在这时是[0, -150]范围//            int viewTop = view.getTop();  // viewTop在这时是[0, -150]范围//            rectWidth = viewLeft + width;//            textY = 150;////            if (viewTop > 0) { // 但viewTop在第0项时特殊， 值是在[150, -150]的范围，所以这里要处理一下//                rectWidth = width;//            }//        }////        int top = parent.getPaddingTop();//        int bottom = parent.getHeight() - parent.getPaddingBottom();////        paint.setColor(Color.LTGRAY);//        c.drawRect(0, top, 0, rectWidth, paint);

        String title = callback.getGroup(firstPos);
        c.drawText(title, originalTextX, 150, textPaint);

    }


}
