package cn.six.sup.rv.option_chain.demo2;

import android.content.Context;

import cn.six.sup.rv.option_chain.BaseBinder;
import cn.six.sup.rv.option_chain.BaseBinderAdapter;
import cn.six.sup.rv.option_chain.demo1.TimeStampeStampBinder;

import java.util.ArrayList;
import java.util.List;

public class ExRvAdapter02 extends BaseBinderAdapter {
    private List<BaseBinder> items;

    private Context ctx;
    private List<String> numbers;


    public ExRvAdapter02(Context ctx, List<String> numbers) {
        this.ctx = ctx;
        this.numbers = numbers;
        this.items = new ArrayList<>();

        items.add(new TimeStampeStampBinder("Right now : "));
        items.add(new XxViewBinder(ctx, numbers));
        items.add(new TimeStampeStampBinder("Another   : "));
    }

    @Override
    public List<BaseBinder> getItems() {
        return items;
    }
}
