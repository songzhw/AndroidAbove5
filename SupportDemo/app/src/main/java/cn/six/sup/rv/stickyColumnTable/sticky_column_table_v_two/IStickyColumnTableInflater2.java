package cn.six.sup.rv.stickyColumnTable.sticky_column_table_v_two;

import cn.six.sup.rv.RvViewHolder;

public interface IStickyColumnTableInflater2<T> {
    void bindLeft(RvViewHolder vh, T s, int position);
    void bindRight(RvViewHolder vh, T s, int position);
}