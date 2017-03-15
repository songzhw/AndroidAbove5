package cn.six.sup.rv.swipe_card;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by songzhw on 2016-11-12
 */
// TODO: 2016-11-12 later
public class SwipeCardLayoutManager extends RecyclerView.LayoutManager {


    @Override // from LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override // from LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler rv, RecyclerView.State state) {
        super.onLayoutChildren(rv, state);
        removeAllViews();
        detachAndScrapAttachedViews(rv); // rv's method, detach all the children views

        int childCount = this.getItemCount();
        for (int i = 0; i < childCount; i++) {
            View child = rv.getViewForPosition(i);
            addView(child);

            // measure child
            this.measureChildWithMargins(child, 0, 0); //rv's method. 后两参会参与width,height的计算

            // layout child
            int width = this.getDecoratedMeasuredWidth(child);
            int height = this.getDecoratedMeasuredHeight(child);
            // 每个child的left和top都一样，直接设置为0就可以了，这样child就依次叠加在一起了
            this.layoutDecoratedWithMargins(child, 0, 0, width, height);

            if(i < (childCount - 1)){
                child.setScaleX(1.2f);
                child.setScaleY(1.2f);
            }
        }
    }
}
