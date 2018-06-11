package cn.six.sup.rv;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.rv.RvViewHolder;

public abstract class OneAdapter<T> extends RecyclerView.Adapter<RvViewHolder> {
    public int layoutResId;
    public List<T> data;

    public OneAdapter(int layoutResId) {
        this.layoutResId = layoutResId;
        data = new ArrayList<>();
    }

    public OneAdapter(int layoutResId, List<T> data) {
        this.layoutResId = layoutResId;
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        System.out.println("szw onCreateViewHolder()");
        RvViewHolder vh = RvViewHolder.createViewHolder(parent, layoutResId);
        return vh;
    }

    @Override
    public void onBindViewHolder(RvViewHolder holder, int position) {
//        System.out.println("szw onBindViewHolder( "+position+" )");
        if (data != null && data.size() > position) {
            apply(holder, data.get(position), position);
        }
    }


    protected abstract void apply(RvViewHolder vh, T t, int position);

}
/*
在不同的type 的时候 如果该 type 所对应的 viewHolder 还没被初始化的时候，会重新调用 onCreateViewHolder 的， 而其他的还是会只调用 onBindViewHolder。
即: onCreateViewHolder(ViewGroup parent, int viewType)的参数里没有position, 只有viewType

上下滑动时, onBindViewHolder(vh, position)会被反复调用. 这个有点像ListView.getAdapter()中的getView()

 */