package cn.six.sup.rv;

import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by songzhw on 2016-06-09.
 */
public class RvItemDragSwipeCallback extends ItemTouchHelper.Callback {
    public RvItemDragSwipeListener listener;

    public RvItemDragSwipeCallback(RvItemDragSwipeListener listener) {
        this.listener = listener;
    }


    // ========================= ItemTouchHelper.Callback (Basic) =================================
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = 0;
        int dragFlags;
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        listener.onMove(fromPosition, toPosition);
        return true;
    }


    // ========================= ItemTouchHelper.Callback (drag & drop) =================================

    // when selected, show different background color
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(0x33cccccc);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    // back to normal (Called by the ItemTouchHelper when the user interaction with an element is over and it also completed its animation.)
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(0);
    }


    // ========================= ItemTouchHelper.Callback (swipe)  =================================

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        listener.onSwiped(position);
    }

    //TODO when swiped, show different alpha
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    // ========================= ItemTouchHelper.Callback (others)  =================================
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled(); // true
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled(); // true
    }

}