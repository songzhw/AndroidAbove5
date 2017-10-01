package cn.six.sup.rv.custom_layout_mgr.fixed_column.demo.entity;

public class Numbera implements IFixedGridType {
    public int num;

    @Override
    public int getType() {
        return TYPE_NUMBER;
    }
}
