package ca.six.ctlay.anim.parallex.ad

import ca.six.advk.utils.rv.RvViewHolder
import ca.six.ctlay.R
import ca.six.ctlay.utils.rv.compose.ItemView

class SimpleTextItemView(private var value: String) : ItemView {
    override val viewType: Int
        get() = R.layout.item_rv_parralex_ad

    override fun bind(holder: RvViewHolder) {
        holder.setText(R.id.tvItemParallex2, value)
    }
}