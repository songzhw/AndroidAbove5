

package cn.six.sup.rv.rv_in_rv;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.rv.composition.BaseRow;
import cn.six.sup.rv.composition.demo.HeaderRow;
import cn.six.sup.rv.composition.demo.IComposedRvView;
import cn.six.sup.rv.composition.demo.TwoTextRow;

class RvInRvPresenter {

    private IComposedRvView view;

    public RvInRvPresenter(IComposedRvView view) {
        this.view = view;
    }

    public void fetchData() {
        List<BaseRow> items = new ArrayList<>();

        items.add(new HeaderRow("title 11"));
        items.add(new HorizontalRvRow());

        items.add(new HeaderRow("title 22"));
        items.add(new TwoTextRow("21 - left", "21 - right"));
        items.add(new TwoTextRow("22 - left", "22 - right"));
        items.add(new TwoTextRow("23 - left", "23 - right"));

        view.refreshList(items);
    }
}
