package cn.six.sup.rv.composition.demo;

import java.util.List;

import cn.six.sup.rv.composition.demo.data.IEntity;

public interface IComposedRvView {
    void refreshList(List<IEntity> data);
}
