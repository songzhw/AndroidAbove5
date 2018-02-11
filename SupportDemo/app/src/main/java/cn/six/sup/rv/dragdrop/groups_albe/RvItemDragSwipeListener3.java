package cn.six.sup.rv.dragdrop.groups_albe;

import android.support.v7.widget.RecyclerView;

public interface RvItemDragSwipeListener3 {
    void onMove(int fromPosition, int toPosition);

    boolean isDraggable(int adapterPosition);

    void onClearView();

    void onSelectionIsIdle(RecyclerView.ViewHolder viewHolder);

}
