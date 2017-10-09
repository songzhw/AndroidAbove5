package cn.six.sup.rv.option_chain.demo2;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.six.sup.R;


public class XxViewHolder extends RecyclerView.ViewHolder {
    public RecyclerView rvInner;

    public XxViewHolder(View itemView) {
        super(itemView);
        rvInner = (RecyclerView) itemView.findViewById(R.id.rvItem);
    }

    @LayoutRes
    public static int getLayoutId() {
        return R.layout.item_only_rv;
    }
}
