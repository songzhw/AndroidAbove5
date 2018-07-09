package ca.six.ctlay.utils.rv.compose

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ca.six.advk.utils.rv.RvViewHolder

class OneTypesAdapter(private val data: List<ItemView>) : RecyclerView.Adapter<RvViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return data[position].viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder.createViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        data[position].bind(holder)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}