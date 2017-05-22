package cn.six.sup.rv.composition.demo;

import java.util.List;

import cn.six.sup.rv.composition.BaseRow;

public interface IComposedRvView {
    void refreshList(List<BaseRow> data);
}
