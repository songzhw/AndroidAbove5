package cn.six.sup.rv.load_more.deprecated;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import cn.six.sup.rv.RvConstants;
import cn.six.sup.rv.RvViewHolder;

/**
 * Created by songzhw on 2016-08-21
 */

// bug: 到达最后一页， 无法让loadMoreView消失！
// LoadMoreWrapper感觉走了偏锋。 用传统的rv.setOnScrollListener()方法可能更合适。
// 另外， 因onBindViewHolder()导致hitBottomAndHasMore()被多次调用， 怕有隐患

@Deprecated
public class LoadMoreWrapper extends RecyclerView.Adapter<RvViewHolder> {

    private RecyclerView.Adapter<RvViewHolder> innerAdapter;
    private ILoadMoreListener listener;
    public View loadMoreView;

    public LoadMoreWrapper(RecyclerView.Adapter innerAdapter, ILoadMoreListener listener) {
        this.innerAdapter = innerAdapter;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return innerAdapter.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position >= innerAdapter.getItemCount()){
            return RvConstants.TYPE_LOAD_MORE;
        }

        // because the loadMoreView is usually a footer,
        // so the position arg here need not to be changed!
        return innerAdapter.getItemViewType(position);
    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == RvConstants.TYPE_LOAD_MORE){
            RvViewHolder holder = RvViewHolder.createViewHolder(loadMoreView);
            return holder;
        }
        return innerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RvViewHolder holder, int position) {
        if(hitBottomAndHasMore(position)){
            listener.onLoadMore();
            return ;
        }

        // because the loadMoreView is usually a footer,
        // so the position arg here need not to be changed!
        innerAdapter.onBindViewHolder(holder, position);
    }

    private boolean hitBottomAndHasMore(int pos) {
        if (listener == null) {
            return false;
        }
        if (loadMoreView == null) {
            return false;
        }
        boolean ret = listener.hasMore() &&
                (pos >= innerAdapter.getItemCount());
        return ret;
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
                    if(getItemViewType(position) == RvConstants.TYPE_LOAD_MORE){
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
        if(getItemViewType(position) == RvConstants.TYPE_LOAD_MORE){
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if(lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams){
                StaggeredGridLayoutManager.LayoutParams staggerLp = (StaggeredGridLayoutManager.LayoutParams) lp;
                staggerLp.setFullSpan(true);
            }
        }
    }

}
