package ca.six.advk.utils.rv


import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by songzhw on 2016-08-12
 */
class RvViewHolder private constructor(private val convertView: View) : RecyclerView.ViewHolder(convertView) {
    private val views: SparseArrayCompat<View>

    init {
        views = SparseArrayCompat()
    }

    fun <T : View> getView(id: Int): T {
        var view: View? = views.get(id)
        if (view == null) {
            view = convertView.findViewById(id)
            views.put(id, view)
        }
        return view as T
    }

    // ============================================
    fun setText(id: Int, str: String) {
        val tv = getView<TextView>(id)
        tv.text = str
    }

    fun setSrc(id: Int, drawId: Int) {
        val iv = getView<ImageView>(id)
        iv.setImageResource(drawId)
    }

    fun setBackground(id: Int, bgResId: Int) {
        val view = getView<View>(id)
        view.setBackgroundResource(bgResId)
    }

    fun setClickListener(id: Int, listener: View.OnClickListener) {
        val view = getView<View>(id)
        view.setOnClickListener(listener)
    }

    fun setVisibility(id: Int, visibility: Int) {
        val view = getView<View>(id)
        view.visibility = visibility
    }


    companion object {
        fun createViewHolder(itemView: View): RvViewHolder {
            return RvViewHolder(itemView)
        }

        fun createViewHolder(parent: ViewGroup, layoutId: Int): RvViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
            return RvViewHolder(itemView)
        }
    }

}
