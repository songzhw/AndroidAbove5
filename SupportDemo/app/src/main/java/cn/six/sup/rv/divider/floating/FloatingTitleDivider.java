package cn.six.sup.rv.divider.floating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.six.sup.R;

public class FloatingTitleDivider extends RecyclerView.ItemDecoration {
    private Context ctx;
    private int height;
    private Paint paint;
    private IFloatingGroupCallback callback;

    public FloatingTitleDivider(Context ctx, IFloatingGroupCallback callback){
        this.ctx = ctx;
        this.callback = callback;
        height = (int)ctx.getResources().getDimension(R.dimen.activity_vertical_margin);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
    }

    // 这个尺寸，被计入了 RecyclerView 每个 item view 的 padding 中
//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
//
//        // 第二参view is the item. Here is the rlay in R.layout.item_rv
//
//        int position = parent.getChildAdapterPosition(view);
//        RecyclerView.ViewHolder vh = parent.findContainingViewHolder(view);
//
//        if (isFirstItemInGroup(position)) {
////            outRect.bottom = height; // bug:   这是在第0项之后加一个divider
//            outRect.top = height;      // fixed: 这才能保证第0项之前有一个didiver
//        }
//        outRect.bottom = 8;
//    }

    private boolean isFirstItemInGroup(int pos) {
        if(pos == 0 ){
            return true;
        }
        String previousGroupText = callback.getGroup(pos - 1);
        String currentGroupText = callback.getGroup(pos);
        return !(previousGroupText.equalsIgnoreCase(currentGroupText));
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = 8;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int top = child.getBottom() ; // 我们要在下方画分隔线，所以这个top是view的bottom
            int bottom = top + 8;
            System.out.println("szw onDraw() top = "+top);
            c.drawRect(left, top, right, bottom, paint);
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }


}
