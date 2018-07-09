package ca.six.ctlay.utils.rv.compose

import ca.six.advk.utils.rv.RvViewHolder

interface ItemView {
    val viewType: Int

    fun bind(holder: RvViewHolder)
}
