package cn.six.sup.rv.cooridinate_rv_rv_2;

import android.support.v7.widget.RecyclerView;

public class CoordinateRvScrollListener extends MultiRvScrollListener {
    private RecyclerView rvOther;

    public CoordinateRvScrollListener(RecyclerView rvOther) {
        this.rvOther = rvOther;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        rvOther.scrollBy(dx, dy);
    }
}