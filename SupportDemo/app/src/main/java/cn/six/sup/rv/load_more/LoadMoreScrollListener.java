package cn.six.sup.rv.load_more;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by songzhw on 2016-08-22
 * <p/>
 * // TODO this only support LinearLayoutManager
 * // TODO I should expand it
 */
public abstract class LoadMoreScrollListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager layMgr;
    private int lastVisiblePosition, oldNum, totalNum, currentPage = 0;
    private boolean isDuplicatedCalled = true;

    public LoadMoreScrollListener(LinearLayoutManager linearLayoutManager) {
        this.layMgr = linearLayoutManager;
    }

    public void reset() {
        lastVisiblePosition = 0;
        oldNum = 0;
        totalNum = 0;
        currentPage = 0;
        isDuplicatedCalled = true;
    }

//    @Override
//    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//        super.onScrollStateChanged(recyclerView, newState);
//    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //每次滑动的时候要更新最后一条可见的item的id
        totalNum = layMgr.getItemCount();//RecyclerView中的总的条目的数量(此处代表的是可见的和不可见的总数)
        lastVisiblePosition = layMgr.findLastVisibleItemPosition();

        if (isDuplicatedCalled) {
            if (totalNum > oldNum) {
                isDuplicatedCalled = false;
                oldNum = totalNum;
            }
        } else {
            if ((lastVisiblePosition + 2) == totalNum) {
                currentPage++;
                onLoadMore(currentPage);
                isDuplicatedCalled = true;
            }
        }
    }

    public abstract void onLoadMore(int page);

}
