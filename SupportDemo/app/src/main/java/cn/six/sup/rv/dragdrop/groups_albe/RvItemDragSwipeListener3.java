package cn.six.sup.rv.dragdrop.groups_albe;

public interface RvItemDragSwipeListener3 {
    void onMove(int fromPosition, int toPosition);

    boolean isDraggable(int adapterPosition);

    void onClearView();

}
