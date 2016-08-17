package cn.six.sup.rv.empty;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import cn.six.sup.rv.RvViewHolder;

/**
 * Created by songzhw on 2016-08-13
 *
 * @deprecated : the empty will not in the center of the Rv
 * this is not good for UI
 */
public class EmptyWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_EMPTY = -100;

    public RecyclerView.Adapter innerAdapter;
    public View emptyView;

    public EmptyWrapper(RecyclerView.Adapter innerAdapter) {
        this.innerAdapter = innerAdapter;
    }

    @Override
    public int getItemViewType(int position) {
        if(isEmpty()){
            return TYPE_EMPTY;
        }
        return innerAdapter.getItemViewType(position);
    }

    private boolean isEmpty(){
        return innerAdapter == null
                || innerAdapter.getItemCount() == 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(isEmpty()){
            RvViewHolder vh = RvViewHolder.createViewHolder(emptyView);
            return vh;
        }
        return innerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(isEmpty()){
            return;
        }
        innerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return isEmpty() ? 1 : innerAdapter.getItemCount();
    }

    // ====================

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        //TODO grid
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        // TODO staggered
        super.onViewAttachedToWindow(holder);
    }
}
