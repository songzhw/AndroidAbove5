package cn.six.sup.rv.option_chain.demo1;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.rv.option_chain.BaseBinder;
import cn.six.sup.rv.option_chain.BaseBinderAdapter;

/**
 * Created by songz2 on 11/25/2016.
 */
public class ExRvAdapter01 extends BaseBinderAdapter {
    private List<BaseBinder> items;

    public ExRvAdapter01() {
        this.items = new ArrayList<>();
        items.add(new TimeStampeStampBinder("Right now : "));
        items.add(new TimeStampeStampBinder("Another   : "));
    }

    @Override
    public List<BaseBinder> getItems() {
        return items;
    }
}
