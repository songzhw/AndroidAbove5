package cn.six.sup.rv.divider.floating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.six.sup.R;

public class FloatingTitleDivider extends RecyclerView.ItemDecoration {
    private Context ctx;
    private int height;
    private IFloatingGroupCallback callback;

    public FloatingTitleDivider(Context ctx, IFloatingGroupCallback callback){
        this.ctx = ctx;
        this.callback = callback;
        height = (int)ctx.getResources().getDimension(R.dimen.activity_vertical_margin);
    }

    // 这个尺寸，被计入了 RecyclerView 每个 item view 的 padding 中
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        // 01.
        // System.out.println("szw view = "+view); // view is the item. Here is the rlay in R.layout.item_rv

        // 02.
        int position = parent.getChildAdapterPosition(view);
        RecyclerView.ViewHolder vh = parent.findContainingViewHolder(view);

        if (isFirstItemInGroup(position)) {
            outRect.bottom = height;
        }

    }

    private boolean isFirstItemInGroup(int pos) {
        if(pos == 0 ){
            return true;
        }
        String previousGroupText = callback.getGroup(pos - 1);
        String currentGroupText = callback.getGroup(pos);
        return !(previousGroupText.equalsIgnoreCase(currentGroupText));
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }


}