package cn.six.sup.rv.one_types_adapter;

import android.view.ViewGroup;

import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;

public abstract class OneTypesAdapter<T> extends OneAdapter<T> {

    public OneTypesAdapter(TypesHelper<T> helper) {
        super(-1);
        this.helper = helper;
    }

    @Override
    public int getItemViewType(int position) {
        return helper.getItemViewType(position, data.get(position));
    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutResId = helper.getLayoutResId(viewType);
        RvViewHolder holder = RvViewHolder.createViewHolder(parent, layoutResId);
        return holder;
    }

    private TypesHelper<T> helper;
}