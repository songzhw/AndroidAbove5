package cn.six.sup.rv.custom_layout_mgr.fixed_column.demo.entity;

public class Name implements IFixedGridType {
    public String name;

    @Override
    public int getType() {
        return 11;
    }

}
