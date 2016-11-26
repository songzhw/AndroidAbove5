package cn.six.sup.rv.option_chain;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseBinder<T extends RecyclerView.ViewHolder> {

    protected View getView(@LayoutRes int layoutId, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    public int getViewType() {
        return this.getClass().getSimpleName().hashCode();
    }

    abstract public T createViewHolder(ViewGroup parent);

    abstract public void bindViewHolder(T holder);

}