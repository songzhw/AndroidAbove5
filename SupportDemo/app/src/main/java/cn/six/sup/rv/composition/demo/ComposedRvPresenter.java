package cn.six.sup.rv.composition.demo;

import java.util.List;

import cn.six.sup.rv.composition.BaseRow;
import cn.six.sup.rv.composition.demo.data.EntityGateway;
import cn.six.sup.rv.composition.demo.data.HomeUseCase;

public class ComposedRvPresenter {
    private IComposedRvView view;
    private HomeUseCase useCase;

    public ComposedRvPresenter(IComposedRvView view) {
        this.view = view;
        EntityGateway gateway = new EntityGateway();
        useCase = new HomeUseCase(gateway);
    }

    public void getData() {
        List<BaseRow> list = useCase.getHomeRows();
        view.refreshList(list);
    }

}
