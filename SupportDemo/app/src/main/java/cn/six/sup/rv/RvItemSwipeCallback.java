package cn.six.sup.rv;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;


public class RvItemSwipeCallback extends ItemTouchHelper.Callback {
    private final RvItemSwipeListener listener;

    public RvItemSwipeCallback(RvItemSwipeListener listener) {
        this.listener = listener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int position = viewHolder.getAdapterPosition();
        if(listener.isDragable(position)){
            int dragFlags = 0;
            return makeMovementFlags(dragFlags, ItemTouchHelper.START | ItemTouchHelper.END);
        }
        return 0;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder.getAdapterPosition());
    }
}
