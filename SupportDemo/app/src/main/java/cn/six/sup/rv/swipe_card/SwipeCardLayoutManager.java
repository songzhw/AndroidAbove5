package cn.six.sup.rv.swipe_card;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by songzhw on 2016-11-12
 */
public class SwipeCardLayoutManager extends RecyclerView.LayoutManager {


    @Override // from LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override // from LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler rv, RecyclerView.State state) {
        this.detachAndScrapAttachedViews(rv); // rv's method, detach all the children views

        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = rv.getViewForPosition(i);

            // measure child
            this.measureChildWithMargins(child, 0, 0); //rv's method. 后两参会参与width,height的计算
            addView(child);

            // layout child
            int width = this.getDecoratedMeasuredWidth(child);
            int height = this.getDecoratedMeasuredHeight(child);
            // 每个child的left和top都一样，直接设置为0就可以了，这样child就依次叠加在一起了
            this.layoutDecorated(child, 0, 0, width, height);

            if(i < (childCount - 1)){
                child.setScaleX(0.8f);
                child.setScaleY(0.8f);
            }
        }
    }
}
