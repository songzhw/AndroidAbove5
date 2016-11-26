package cn.six.sup.rv.option_chain.demo1;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ca.six.testt.R;


public class TimeStampViewHolder extends RecyclerView.ViewHolder {
    public TextView tvTimestamp;

    public TimeStampViewHolder(View itemView) {
        super(itemView);
        tvTimestamp = (TextView) itemView.findViewById(R.id.tvTimeStamp);
    }

    @LayoutRes
    public static int getLayoutId() {
        return R.layout.item_rv_timestamp;
    }
}