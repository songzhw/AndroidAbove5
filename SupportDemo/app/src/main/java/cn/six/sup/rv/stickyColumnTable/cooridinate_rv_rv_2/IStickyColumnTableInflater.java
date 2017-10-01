package cn.six.sup.rv.stickyColumnTable.cooridinate_rv_rv_2;

import cn.six.sup.rv.RvViewHolder;

public interface IStickyColumnTableInflater<T> {
    void bindLeft (RvViewHolder vh, T s, int position);
    void bindRight(RvViewHolder vh, T s, int position);
}
