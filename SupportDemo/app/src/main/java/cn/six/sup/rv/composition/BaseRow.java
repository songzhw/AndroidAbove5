package cn.six.sup.rv.composition;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import cn.six.sup.rv.RvViewHolder;

public abstract class BaseRow {
    public static final int TYPE_UNDO = 99;
    public static final int TYPE_HEADER = 3;
    public static final int TYPE_TWO_TEXT = 4;
    public static final int TYPE_CLOTH = 5;
    public static final int TYPE_STICKY_TABLE= 6;


    protected abstract int getViewType();

    RvViewHolder getViewHolder(ViewGroup parent) {
        return RvViewHolder.createViewHolder(parent, getLayoutXmlRes());
    }

    protected abstract @LayoutRes
    int getLayoutXmlRes();

    protected abstract void bind(RvViewHolder holder);

    public abstract boolean isDraggable();
}
