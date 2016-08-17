package cn.six.sup.rv.group_adapter.entity;

import java.util.List;

import cn.six.sup.rv.group_adapter.IEntityInfo;

/**
 * Created by songzhw on 2016-08-15
 */
public class City implements IEntityInfo{
    public String name;

    public City(String name) {
        this.name = name;
    }

    @Override
    public int getChildrenCount() {
        return 0;
    }

    @Override
    public List getChildren() {
        return null;
    }

    @Override
    public int getType() {
        return 10;
    }
}
