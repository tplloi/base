package com.views.viewpager.autoviewpager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import com.R

class AutoViewPager : ViewPager {
    private var duration = DEFAULT_DURATION
    private var startX: Float = 0.toFloat()
    private var autoScrollEnabled: Boolean = false
    private var indeterminate: Boolean = false

    private val autoScroll = object : Runnable {
        override fun run() {
            if (!isShown) {
                return
            }
            if (adapter == null) {
                return
            }
            currentItem = if (currentItem == adapter!!.count - 1) {
                0
            } else {
                currentItem + 1
            }
            handler.postDelayed(this, duration.toLong())
        }
    }

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AutoViewPager)
        try {
            setAutoScrollEnabled(a.getBoolean(R.styleable.AutoViewPager_avp_autoScroll, false))
            setIndeterminate(a.getBoolean(R.styleable.AutoViewPager_avp_indeterminate, false))
            setDuration(a.getInteger(R.styleable.AutoViewPager_avp_duration, DEFAULT_DURATION))
        } finally {
            a.recycle()
        }
    }

    fun setIndeterminate(indeterminate: Boolean) {
        this.indeterminate = indeterminate
    }

    fun setAutoScrollEnabled(enabled: Boolean) {
        if (autoScrollEnabled == enabled) {
            return
        }
        autoScrollEnabled = enabled
        stopAutoScroll()
        if (enabled) {
            startAutoScroll()
        }
    }

    fun setDuration(duration: Int) {
        this.duration = duration
    }

    private fun startAutoScroll() {
        postDelayed(autoScroll, duration.toLong())
    }

    private fun stopAutoScroll() {
        removeCallbacks(autoScroll)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        try {
            val action = event.actionMasked
            when (action) {
                MotionEvent.ACTION_DOWN -> startX = event.x
            }
            return super.onInterceptTouchEvent(event)
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }

        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        try {
            if (indeterminate) {
                if (currentItem == 0 || currentItem == adapter!!.count - 1) {
                    val action = event.action
                    val x = event.x
                    when (action and MotionEvent.ACTION_MASK) {
                        MotionEvent.ACTION_UP -> if (currentItem == adapter!!.count - 1 && x < startX) {
                            post { currentItem = 0 }
                        }
                    }
                } else {
                    startX = 0f
                }
            }
            return super.onTouchEvent(event)
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }

        return false
    }

    companion object {
        private val TAG = AutoViewPager::class.java.simpleName
        private const val DEFAULT_DURATION = 1000
    }
}