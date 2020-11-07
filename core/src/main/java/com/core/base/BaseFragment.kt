package com.core.base

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.R
import com.annotation.LogTag
import com.core.common.Constants
import com.core.utilities.LDialogUtil
import com.core.utilities.LLog
import com.data.EventBusData
import com.interfaces.Callback1
import com.views.LToast
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseFragment : Fragment() {
    protected var logTag: String? = null
    protected var compositeDisposable = CompositeDisposable()
    protected var frmRootView: View? = null

    companion object {
        private const val DEFAULT_CHILD_ANIMATION_DURATION = 400
    }

    protected abstract fun setLayoutResourceId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        frmRootView = inflater.inflate(setLayoutResourceId(), container, false)
        val tmpLogTag = javaClass.getAnnotation(LogTag::class.java)
        logTag = "logTag" + tmpLogTag?.value
        EventBus.getDefault().register(this)
        return frmRootView
    }

    override fun onDestroyView() {
        LDialogUtil.clearAll()
        compositeDisposable.clear()
        EventBus.getDefault().unregister(this)
        super.onDestroyView()
    }

    protected fun logD(msg: String) {
        logTag?.let {
            LLog.d(tag = it, msg = msg)
        }
    }

    protected fun logE(msg: String) {
        logTag?.let {
            LLog.e(tag = it, msg = msg)
        }
    }

    open fun handleException(throwable: Throwable) {
        throwable.message?.let {
            showDialogError(errMsg = it)
        }
    }

    open fun showDialogError(errMsg: String, runnable: Runnable? = null) {
        context?.let {
            LDialogUtil.showDialog1(context = it,
                    title = getString(R.string.warning),
                    msg = errMsg,
                    button1 = getString(R.string.confirm),
                    callback1 = object : Callback1 {
                        override fun onClick1() {
                            runnable?.run()
                        }
                    })
        }
    }

    open fun showDialogMsg(msg: String, runnable: Runnable? = null) {
        context?.let {
            LDialogUtil.showDialog1(context = it,
                    title = getString(R.string.app_name),
                    msg = msg,
                    button1 = getString(R.string.confirm),
                    callback1 = object : Callback1 {
                        override fun onClick1() {
                            runnable?.run()
                        }
                    }
            )
        }
    }

    //https://stackoverflow.com/questions/14900738/nested-fragments-disappear-during-transition-animation
    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        val parent = parentFragment

        // Apply the workaround only if this is a child fragment, and the parent
        // is being removed.
        return if (!enter && parent != null && parent.isRemoving) {
            // This is a workaround for the bug where child fragments disappear when
            // the parent is removed (as all children are first removed from the parent)
            // See https://code.google.com/p/android/issues/detail?id=55228
            val doNothingAnim = AlphaAnimation(1f, 1f)
            doNothingAnim.duration = getNextAnimationDuration(parent, DEFAULT_CHILD_ANIMATION_DURATION.toLong())
            doNothingAnim
        } else {
            super.onCreateAnimation(transit, enter, nextAnim)
        }
    }

    private fun getNextAnimationDuration(fragment: Fragment, defValue: Long): Long {
        try {
            // Attempt to get the resource ID of the next animation that
            // will be applied to the given fragment.
            val nextAnimField = Fragment::class.java.getDeclaredField("mNextAnim")
            nextAnimField.isAccessible = true
            val nextAnimResource = nextAnimField.getInt(fragment)
            val nextAnim = AnimationUtils.loadAnimation(fragment.activity, nextAnimResource)

            // ...and if it can be loaded, return that animation's duration
            return nextAnim?.duration ?: defValue
        } catch (ex: NoSuchFieldException) {
            //logD("Unable to load next animation from parent.", ex)
            ex.printStackTrace()
            return defValue
        } catch (ex: IllegalAccessException) {
            ex.printStackTrace()
            return defValue
        } catch (ex: Resources.NotFoundException) {
            ex.printStackTrace()
            return defValue
        }
    }

    protected fun showShortInformation(msg: String?, isTopAnchor: Boolean = true) {
        LToast.showShortInformation(msg = msg, isTopAnchor = isTopAnchor)
    }

    protected fun showShortWarning(msg: String?, isTopAnchor: Boolean = true) {
        LToast.showShortWarning(msg = msg, isTopAnchor = isTopAnchor)
    }

    protected fun showShortError(msg: String?, isTopAnchor: Boolean = true) {
        LToast.showShortError(msg = msg, isTopAnchor = isTopAnchor)
    }

    protected fun showLongInformation(msg: String?, isTopAnchor: Boolean = true) {
        LToast.showLongInformation(msg = msg, isTopAnchor = isTopAnchor)
    }

    protected fun showLongWarning(msg: String?, isTopAnchor: Boolean = true) {
        LToast.showLongWarning(msg = msg, isTopAnchor = isTopAnchor)
    }

    protected fun showLongError(msg: String?, isTopAnchor: Boolean = true) {
        LToast.showLongError(msg = msg, isTopAnchor = isTopAnchor)
    }

    protected fun showShortDebug(msg: String?) {
        if (Constants.IS_DEBUG) {
            LToast.showShortDebug(msg)
        }
    }

    protected fun showLongDebug(msg: String?) {
        if (Constants.IS_DEBUG) {
            LToast.showLongInformation(msg)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: EventBusData.ConnectEvent) {
        onNetworkChange(event)
    }

    open fun onNetworkChange(event: EventBusData.ConnectEvent) {
    }

    protected fun <T : ViewModel> getViewModel(className: Class<T>): T? {
        return activity?.let {
            ViewModelProvider(it).get(className)
        }
    }

    protected fun <T : ViewModel> getSelfViewModel(className: Class<T>): T {
        return ViewModelProvider(this).get(className)
    }
}
