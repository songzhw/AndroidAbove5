package cn.six.sup.rv.card_stack;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by songzhw on 2017-03-15
 */

public class CardsLayoutManager extends RecyclerView.LayoutManager {
    public static final int MAX_INDEX = 3; // 即显示4个
    public static final float SCALE = 0.1f;
    public static final int TRANSLATE_Y = 14;

    private RecyclerView rv;
    private ItemTouchHelper helper;
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            RecyclerView.ViewHolder vh = rv.getChildViewHolder(v);
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                helper.startSwipe(vh);
            }
            return false;
        }
    };

    public CardsLayoutManager(RecyclerView rv, ItemTouchHelper helper) {
        this.rv = rv;
        this.helper = helper;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        removeAllViews();
        detachAndScrapAttachedViews(recycler);

        int count = getItemCount();
        int visibleMaxIndex = count - 1;
        if (count > MAX_INDEX) {
            visibleMaxIndex = MAX_INDEX;
        }

        for (int pos = visibleMaxIndex; pos >= 0; pos--) {
            View view = recycler.getViewForPosition(pos);
            addView(view);

            measureChildWithMargins(view, 0, 0);
            int viewWidth = getDecoratedMeasuredWidth(view);
            int viewHeight = getDecoratedMeasuredHeight(view);
            int spaceWidth = getWidth() - viewWidth;
            int spaceHeight = getHeight() - viewHeight;

            layoutDecoratedWithMargins(view, spaceWidth / 2, spaceHeight / 2,
                    spaceWidth / 2 + viewWidth, spaceHeight / 2 + viewHeight);

            if (pos > 0) {
                int index = pos;
                if (pos == MAX_INDEX) {
                    index = pos - 1;
                }
                view.setScaleX(1 - index * SCALE);
                view.setScaleY(1 - index * SCALE);
                view.setTranslationY(index * view.getMeasuredHeight() / TRANSLATE_Y);
            } else {
                view.setOnTouchListener(touchListener);
            }
        }
    }
}
