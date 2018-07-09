package ca.six.ctlay.utils

import android.content.Context

fun Int.px2dp(context: Context): Int {
    val scale = context.getResources().getDisplayMetrics().density
    return (this / scale + 0.5f).toInt()
}

fun Int.dp2px(context: Context): Int {
    val scale = context.resources.displayMetrics.density
    return (this * scale + 0.5f).toInt()
}

