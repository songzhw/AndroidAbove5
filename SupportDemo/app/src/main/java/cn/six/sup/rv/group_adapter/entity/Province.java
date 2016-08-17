package cn.six.sup.rv.group_adapter.entity;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.rv.group_adapter.IEntityInfo;

/**
 * Created by songzhw on 2016-08-15
 */
public class Province implements IEntityInfo {
    public String name;
    private List<City> cities;

    public Province(String name) {
        this.name = name;
    }

    public void addCity(String c) {
        if (cities == null) {
            cities = new ArrayList<>();
        }
        cities.add(new City(c));
    }

    @Override
    public int getChildrenCount() {
        return cities == null ? 0 : cities.size();
    }

    @Override
    public List<? extends IEntityInfo> getChildren() {
        return cities;
    }

    @Override
    public int getType() {
        return 11;
    }

}
