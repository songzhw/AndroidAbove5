package ca.six.advk.utils.rv


import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import java.util.ArrayList

abstract class OneAdapter<T> : RecyclerView.Adapter<RvViewHolder> {
    var layoutResId: Int = 0
    var data: List<T>? = null

    constructor(layoutResId: Int) {
        this.layoutResId = layoutResId
        data = ArrayList()
    }

    constructor(layoutResId: Int, data: List<T>) {
        this.layoutResId = layoutResId
        this.data = data
    }

    override fun getItemCount(): Int {
        return if (data == null) 0 else data!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        //        System.out.println("szw onCreateViewHolder()");
        return RvViewHolder.createViewHolder(parent, layoutResId)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        //        System.out.println("szw onBindViewHolder( "+position+" )");
        if (data != null && data!!.size > position) {
            apply(holder, data!![position], position)
        }
    }


    protected abstract fun apply(vh: RvViewHolder, value: T, position: Int)

}
/*
在不同的type 的时候 如果该 type 所对应的 viewHolder 还没被初始化的时候，会重新调用 onCreateViewHolder 的， 而其他的还是会只调用 onBindViewHolder。
即: onCreateViewHolder(ViewGroup parent, int viewType)的参数里没有position, 只有viewType

上下滑动时, onBindViewHolder(vh, position)会被反复调用. 这个有点像ListView.getAdapter()中的getView()

 */