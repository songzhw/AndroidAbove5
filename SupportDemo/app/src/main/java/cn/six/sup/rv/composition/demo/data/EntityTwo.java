package cn.six.sup.rv.composition.demo.data;

import cn.six.sup.rv.composition.BaseRow;

public class EntityTwo implements IEntity{
    public String left;
    public String right;

    public EntityTwo(String left, String right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int getType() {
        return BaseRow.TYPE_TWO_TEXT;
    }
}
