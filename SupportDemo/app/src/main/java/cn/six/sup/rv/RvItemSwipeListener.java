package cn.six.sup.rv;

public interface RvItemSwipeListener {
    boolean isDragable(int position);

    void onSwiped(int position);
}
