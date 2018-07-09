package ca.six.ctlay.anim.parallex.ad

import ca.six.advk.utils.rv.RvViewHolder
import ca.six.ctlay.R
import ca.six.ctlay.utils.rv.compose.ItemView

class ImageItemView(private var imageResId : Int) : ItemView {
    override val viewType: Int
        get() = R.layout.item_rv_parralex_ad_img

    override fun bind(holder: RvViewHolder) {
        holder.setSrc(R.id.ivItemParallexAd, imageResId)
    }
}