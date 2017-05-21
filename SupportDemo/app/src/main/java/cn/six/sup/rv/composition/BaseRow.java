package cn.six.sup.rv.composition;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import cn.six.sup.rv.RvViewHolder;

public abstract class BaseRow {
    protected static final int TYPE_HEADER = 3;
    protected static final int TYPE_TWO_TEXT = 4;


    protected abstract int getViewType();

    RvViewHolder getViewHolder(ViewGroup parent){
        return RvViewHolder.createViewHolder(parent, getLayoutXmlRes());
    }

    protected abstract @LayoutRes int getLayoutXmlRes();

    protected abstract void bind(RvViewHolder holder);
}
