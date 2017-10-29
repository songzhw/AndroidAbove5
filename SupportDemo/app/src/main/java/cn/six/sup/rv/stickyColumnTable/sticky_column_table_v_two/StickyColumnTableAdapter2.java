package cn.six.sup.rv.stickyColumnTable.sticky_column_table_v_two;

import java.util.List;

public class StickyColumnTableAdapter2<T> {
    private List<T> leftData;
    private List<T> rightData;

    public StickyColumnTableAdapter2(List<T> leftData, List<T> rightData) {
        this.leftData = leftData;
        this.rightData = rightData;
    }

    public List<T> getLeftData() {
        return leftData;
    }

    public List<T> getRightData() {
        return rightData;
    }
}
