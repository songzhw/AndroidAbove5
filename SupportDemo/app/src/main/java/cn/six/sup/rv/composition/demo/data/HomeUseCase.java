package cn.six.sup.rv.composition.demo.data;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.rv.composition.BaseRow;
import cn.six.sup.rv.composition.demo.HeaderRow;
import cn.six.sup.rv.composition.demo.TwoTextRow;

public class HomeUseCase {
    private EntityGateway gateway;

    public HomeUseCase(EntityGateway gateway) {
        this.gateway = gateway;
    }

    public List<BaseRow> getHomeRows(){
        List<BaseRow> items = new ArrayList<>();

        for(IEntity raw : gateway.getHomeData()){
            int type = raw.getType();
            if(BaseRow.TYPE_HEADER == type){
                EntityHeader header = (EntityHeader) raw;
                items.add(new HeaderRow(header.title, header.caption));
            } else if (BaseRow.TYPE_TWO_TEXT == type){
                EntityTwo two = (EntityTwo) raw;
                items.add(new TwoTextRow(two.left, two.right));
            }
        }

        return items;
    }
}
