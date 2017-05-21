package cn.six.sup.rv.composition;

import android.view.ViewGroup;

import cn.six.sup.rv.RvViewHolder;

public abstract class BaseRow {
    protected static final int TYPE_HEADER = 3;
    protected static final int TYPE_TWO_TEXT = 4;


    protected abstract int getViewType();

    protected abstract RvViewHolder getViewHolder(ViewGroup parent);

    protected abstract void bind(RvViewHolder holder);
}
