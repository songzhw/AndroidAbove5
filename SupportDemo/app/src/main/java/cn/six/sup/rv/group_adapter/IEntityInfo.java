package cn.six.sup.rv.group_adapter;

import java.util.List;

/**
 * Created by songzhw on 2016-08-15
 */
public interface IEntityInfo {
    int getChildrenCount();

    List<? extends IEntityInfo> getChildren();

    int getType();
}
