package cn.six.sup.rv.header;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.six.sup.rv.RvViewHolder;

/**
 * Created by songzhw on 2016-08-12
 */
public class HeaderWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 1;
    public static final int TYPE_CONTENT = 9;

    public View headerView;
    public RecyclerView.Adapter innerAdapter;

    public HeaderWrapper(RecyclerView.Adapter innerAdapter) {
        this.innerAdapter = innerAdapter;
    }

    private int getInnerItemCount(){
        return innerAdapter.getItemCount();
    }

    @Override
    public int getItemCount() {
        return getInnerItemCount() + 1; // inner + header
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_HEADER;
        }
        return TYPE_CONTENT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER){
            return RvViewHolder.createViewHolder(headerView);
        }
        return innerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_HEADER){
            return;
        }
        innerAdapter.onBindViewHolder(holder, position);
    }

    // ===================================================

    // deal with header in the GridLayoutMangaer
    @Override
    public void onAttachedToRecyclerView(RecyclerView rv) {
        innerAdapter.onAttachedToRecyclerView(rv);
        RecyclerView.LayoutManager layMgr = rv.getLayoutManager();
        if(layMgr instanceof GridLayoutManager){
            final GridLayoutManager lay = (GridLayoutManager) layMgr;
            lay.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(getItemViewType(position) == TYPE_HEADER){
                        return lay.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    // deal with header in the StaggerGridLayoutMangaer
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        innerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if(getItemViewType(position) == TYPE_HEADER){
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if(lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams){
                StaggeredGridLayoutManager.LayoutParams staggerLp = (StaggeredGridLayoutManager.LayoutParams) lp;
                staggerLp.setFullSpan(true);
            }
        }
    }
}
