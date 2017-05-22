package cn.six.sup.rv.composition.demo.data;

import cn.six.sup.rv.composition.BaseRow;

public class EntityHeader implements IEntity{
    public String title;
    public String caption;

    public EntityHeader(String title, String caption) {
        this.title = title;
        this.caption = caption;
    }

    @Override
    public int getType() {
        return BaseRow.TYPE_HEADER;
    }
}
