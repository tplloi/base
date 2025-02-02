package com.loitp.core.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.loitp.annotation.LogTag
import com.loitp.core.ext.d
import com.loitp.core.ext.e
import com.loitp.views.toast.LToast

// https://github.com/wasabeef/recyclerview-animators

/*bookAdapter?.let {
    val animAdapter = AlphaInAnimationAdapter(it)
    val animAdapter = ScaleInAnimationAdapter(it)
    val animAdapter = SlideInBottomAnimationAdapter(it)
    val animAdapter = SlideInLeftAnimationAdapter(it)
    val animAdapter = SlideInRightAnimationAdapter(it)

    animAdapter.setDuration(1000)
    animAdapter.setInterpolator(OvershootInterpolator())
    animAdapter.setFirstOnly(true)
    rv.adapter = animAdapter
}*/

abstract class BaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected var logTag: String? = null

    init {
        logTag = javaClass.getAnnotation(LogTag::class.java)?.value
    }

    protected fun logD(msg: String) {
        logTag?.let {
            d(it, msg)
        }
    }

    protected fun logE(msg: String) {
        logTag?.let {
            e(it, msg)
        }
    }

    protected fun showShortInformation(
        msg: String?,
        isTopAnchor: Boolean = true
    ) {
        LToast.showShortInformation(msg = msg, isTopAnchor = isTopAnchor)
    }

    @Suppress("unused")
    protected fun showShortWarning(
        msg: String?,
        isTopAnchor: Boolean = true
    ) {
        LToast.showShortWarning(msg = msg, isTopAnchor = isTopAnchor)
    }

    protected fun showShortError(
        msg: String?,
        isTopAnchor: Boolean = true
    ) {
        LToast.showShortError(msg = msg, isTopAnchor = isTopAnchor)
    }

    @Suppress("unused")
    protected fun showLongInformation(
        msg: String?,
        isTopAnchor: Boolean = true
    ) {
        LToast.showLongInformation(msg = msg, isTopAnchor = isTopAnchor)
    }

    @Suppress("unused")
    protected fun showLongWarning(
        msg: String?,
        isTopAnchor: Boolean = true
    ) {
        LToast.showLongWarning(msg = msg, isTopAnchor = isTopAnchor)
    }

    @Suppress("unused")
    protected fun showLongError(
        msg: String?,
        isTopAnchor: Boolean = true
    ) {
        LToast.showLongError(msg = msg, isTopAnchor = isTopAnchor)
    }

    @Suppress("unused")
    protected fun showShortDebug(msg: String?) {
        LToast.showShortDebug(msg)
    }

    @Suppress("unused")
    protected fun showLongDebug(msg: String?) {
        LToast.showLongDebug(msg)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun notifyAllViews() {
        try {
            notifyItemRangeChanged(0, itemCount)
        } catch (e: java.lang.Exception) {
            notifyDataSetChanged()
        }
    }
}
