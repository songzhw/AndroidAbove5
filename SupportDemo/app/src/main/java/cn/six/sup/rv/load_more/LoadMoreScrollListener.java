package cn.six.sup.rv.load_more;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by songzhw on 2016-08-22
 *
 * // TODO this only support LinearLayoutManager
 * // TODO I should expand it
 */
public abstract class LoadMoreScrollListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager layMgr;
    private int lastVisiblePosition;

    public LoadMoreScrollListener(LinearLayoutManager linearLayoutManager) {
        this.layMgr = linearLayoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        int totalCount = layMgr.getItemCount();//RecyclerView中的总的条目的数量(此处代表的是可见的和不可见的总数)
        if(newState == RecyclerView.SCROLL_STATE_IDLE && ((lastVisiblePosition + 1)==totalCount)){
            onLoadMore(lastVisiblePosition);
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //每次滑动的时候要更新最后一条可见的item的id
        lastVisiblePosition = layMgr.findLastVisibleItemPosition();
    }

    public abstract void onLoadMore(int lastPosition);

}
