package cn.six.sup.rv.option_chain.demo2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import cn.six.sup.R;
import cn.six.sup.rv.option_chain.BaseBinder;

import java.util.List;

public class XxViewBinder extends BaseBinder<XxViewHolder> {
    private Context ctx;
    private List<String> numbers;

    public XxViewBinder(Context ctx, List<String> numbers) {
        this.ctx = ctx;
        this.numbers = numbers;
    }

    @Override
    public XxViewHolder createViewHolder(ViewGroup parent) {
        View view = getView(R.layout.item_only_rv, parent);
        return new XxViewHolder(view);
    }

    @Override
    public void bindViewHolder(XxViewHolder holder) {
        holder.rvInner.setLayoutManager(new NestedGridLayoutManager(ctx, 4));
        holder.rvInner.setHasFixedSize(true);
        NumbersAdapter adapter = new NumbersAdapter(ctx, numbers);
        holder.rvInner.setAdapter(adapter);
    }
}
