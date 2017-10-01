package cn.six.sup.rv.custom_layout_mgr.fixed_column;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


public class FixColumnGridLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
