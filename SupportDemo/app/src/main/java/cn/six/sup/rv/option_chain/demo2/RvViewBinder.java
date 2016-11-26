package cn.six.sup.rv.option_chain.demo2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import cn.six.sup.R;
import cn.six.sup.rv.option_chain.BaseBinder;

import java.util.List;

public class RvViewBinder extends BaseBinder<RvViewHolder> {
    private Context ctx;
    private List<String> numbers;

    public RvViewBinder(Context ctx, List<String> numbers) {
        this.ctx = ctx;
        this.numbers = numbers;
    }

    @Override
    public RvViewHolder createViewHolder(ViewGroup parent) {
        View view = getView(R.layout.item_only_rv, parent);
        return new RvViewHolder(view);
    }

    @Override
    public void bindViewHolder(RvViewHolder holder) {
        holder.rvInner.setLayoutManager(new NestedGridLayoutManager(ctx, 4));
        holder.rvInner.setHasFixedSize(true);
        NumbersAdapter adapter = new NumbersAdapter(ctx, numbers);
        holder.rvInner.setAdapter(adapter);
    }
}
