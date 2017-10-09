package cn.six.sup.rv.header;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import cn.six.sup.rv.RvConstants;
import cn.six.sup.rv.RvViewHolder;

/**
 * Created by songzhw on 2016-08-12
 */
public class HeaderWrapper extends RecyclerView.Adapter<RvViewHolder> {
    public View headerView;
    public RecyclerView.Adapter<RvViewHolder> innerAdapter;

    public HeaderWrapper(RecyclerView.Adapter innerAdapter) {
        this.innerAdapter = innerAdapter;
    }

    @Override
    public int getItemCount() {
        return innerAdapter.getItemCount() + 1; // inner + header
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return RvConstants.TYPE_HEADER;
        }
        return innerAdapter.getItemViewType(position - 1);
    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RvConstants.TYPE_HEADER) {
            return RvViewHolder.createViewHolder(headerView);
        }
        return innerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RvViewHolder holder, int position) {
        if (getItemViewType(position) == RvConstants.TYPE_HEADER) {
            return;
        }
        innerAdapter.onBindViewHolder(holder, position - 1);
    }

    // ===================================================

    // deal with header in the GridLayoutMangaer
    @Override
    public void onAttachedToRecyclerView(RecyclerView rv) {
        innerAdapter.onAttachedToRecyclerView(rv);
        RecyclerView.LayoutManager layMgr = rv.getLayoutManager();
        if (layMgr instanceof GridLayoutManager) {
            final GridLayoutManager lay = (GridLayoutManager) layMgr;
            lay.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == RvConstants.TYPE_HEADER) {
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
        if (getItemViewType(position) == RvConstants.TYPE_HEADER) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams staggerLp = (StaggeredGridLayoutManager.LayoutParams) lp;
                staggerLp.setFullSpan(true);
            }
        }
    }
}
