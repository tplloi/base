package vn.loitp.app.activity.customviews.actionbar.navigationview

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import loitp.basemaster.R
import vn.loitp.core.base.BaseFontActivity
import vn.loitp.core.utilities.LLog
import vn.loitp.core.utilities.LUIUtil
import vn.loitp.views.navigationview.LNavigationView
import java.util.*

class NavigationViewActivity : BaseFontActivity() {
    private var nv: LNavigationView? = null
    private var tvMsg: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nv = findViewById(R.id.nv)
        tvMsg = findViewById(R.id.tv_msg)

        nv?.let {
            it.colorOn = ContextCompat.getColor(activity, R.color.Red)
            it.colorOff = ContextCompat.getColor(activity, R.color.Gray)
            it.tv.setTextColor(Color.BLACK)
            LUIUtil.setTextSize(it.tv, TypedValue.COMPLEX_UNIT_DIP, 20)//20dp
        }

        val stringList = ArrayList<String>()
        for (i in 0..9) {
            stringList.add("Item $i")
        }

        nv?.stringList = stringList
        nv?.setNVCallback { index, s ->
            LLog.d(TAG, "onIndexChange $index -> $s")
            tvMsg?.text = "$index -> $s"
        }
        findViewById<View>(R.id.bt_0).setOnClickListener { nv?.currenIndex = 0 }
        findViewById<View>(R.id.bt_1).setOnClickListener { nv?.currenIndex = stringList.size - 1 }
        findViewById<View>(R.id.bt_2).setOnClickListener { nv?.currenIndex = 2 }
    }

    override fun setFullScreen(): Boolean {
        return false
    }

    override fun setTag(): String {
        return javaClass.simpleName
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_navigation_view
    }

}
