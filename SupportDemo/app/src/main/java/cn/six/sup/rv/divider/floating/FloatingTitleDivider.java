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

    public FloatingTitleDivider(Context ctx){
        this.ctx = ctx;
        height = (int)ctx.getResources().getDimension(R.dimen.activity_vertical_margin);
    }


    // 这个尺寸，被计入了 RecyclerView 每个 item view 的 padding 中
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = height;

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