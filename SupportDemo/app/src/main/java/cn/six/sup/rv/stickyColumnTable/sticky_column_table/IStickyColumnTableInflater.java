package cn.six.sup.rv.stickyColumnTable.sticky_column_table;


import cn.six.sup.rv.RvViewHolder;

public interface IStickyColumnTableInflater<T> {
    void bindLeft(RvViewHolder vh, T s, int position);
    void bindRight(RvViewHolder vh, T s, int position);
}
