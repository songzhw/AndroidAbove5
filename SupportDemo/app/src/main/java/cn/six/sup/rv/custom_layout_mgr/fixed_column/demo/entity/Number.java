package cn.six.sup.rv.custom_layout_mgr.fixed_column.demo.entity;

public class Number implements IFixedGridType {
    public int num;

    @Override
    public int getType() {
        return 12;
    }
}
