package cn.six.sup.rv.load_more;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import cn.six.sup.rv.RvConstants;
import cn.six.sup.rv.RvViewHolder;

/**
 * Created by songzhw on 2016-08-22
 */
public class FooterWrapper extends RecyclerView.Adapter<RvViewHolder> {
    public View footView;
    public RecyclerView.Adapter<RvViewHolder> innerAdapter;

    public FooterWrapper(RecyclerView.Adapter innerAdapter) {
        this.innerAdapter = innerAdapter;
    }

    @Override
    public int getItemCount() {
        return innerAdapter.getItemCount() + 1; // inner + footer
    }

    @Override
    public int getItemViewType(int position) {
        if(position >= innerAdapter.getItemCount()){
            return RvConstants.TYPE_FOOTER;
        }
        return innerAdapter.getItemViewType(position);
    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == RvConstants.TYPE_FOOTER){
            return RvViewHolder.createViewHolder(footView);
        }
        return innerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RvViewHolder holder, int position) {
        if(getItemViewType(position) == RvConstants.TYPE_FOOTER){
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
                    if(getItemViewType(position) == RvConstants.TYPE_FOOTER){
                        return lay.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    // deal with header in the StaggerGridLayoutMangaer
    @Override
    public void onViewAttachedToWindow(RvViewHolder holder) {
        innerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if(getItemViewType(position) == RvConstants.TYPE_FOOTER){
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if(lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams){
                StaggeredGridLayoutManager.LayoutParams staggerLp = (StaggeredGridLayoutManager.LayoutParams) lp;
                staggerLp.setFullSpan(true);
            }
        }
    }
}
