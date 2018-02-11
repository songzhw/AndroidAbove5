package cn.six.sup.rv.dragdrop.groups_albe;

import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
* Created by songzhw on 2017-12-18.
*/
public class RvItemDragSwipeCallback3 extends ItemTouchHelper.Callback {
    public RvItemDragSwipeListener3 listener;

    public RvItemDragSwipeCallback3(RvItemDragSwipeListener3 listener) {
        this.listener = listener;
    }


    // ========================= ItemTouchHelper.Callback (Basic) =================================
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        System.out.println("szw getMovementFlags("+viewHolder.getAdapterPosition()+")");
        int noSwipeFlags = 0;
        int dragFlags;
        if(listener.isDraggable(viewHolder.getAdapterPosition())) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        } else {
            dragFlags = 0;
        }
        return makeMovementFlags(dragFlags, noSwipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        System.out.println("szw onMove() from "+viewHolder.getAdapterPosition()+" to "+target.getAdapterPosition());
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        listener.onMove(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        // do nothing
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

}