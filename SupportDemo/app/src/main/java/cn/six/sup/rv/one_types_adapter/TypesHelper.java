package cn.six.sup.rv.one_types_adapter;

public interface TypesHelper<T> {
    int getLayoutResId(int itemType);
    int getItemViewType(int position, T t);
}
