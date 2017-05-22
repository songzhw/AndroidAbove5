package cn.six.sup.rv.composition.demo;

import java.util.List;

import cn.six.sup.rv.composition.demo.data.EntityGateway;
import cn.six.sup.rv.composition.demo.data.IEntity;

public class ComposedRvPresenter {
    private IComposedRvView view;
    private EntityGateway model;

    public ComposedRvPresenter(IComposedRvView view) {
        this.view = view;
        this.model = new EntityGateway();
    }

    public void getData() {
        List<IEntity> list = model.getHomeData();
        view.refreshList(list);
    }

}
