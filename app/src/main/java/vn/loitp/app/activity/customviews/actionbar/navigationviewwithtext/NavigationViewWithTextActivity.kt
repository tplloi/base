package vn.loitp.app.activity.customviews.actionbar.navigationviewwithtext

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import com.loitpcore.annotation.IsFullScreen
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFontActivity
import com.loitpcore.core.utilities.LAppResource
import com.loitpcore.views.navigationview.LTextNavigationView
import kotlinx.android.synthetic.main.activity_navigation_view_with_text.*
import vn.loitp.app.R
import java.util.* // ktlint-disable no-wildcard-imports

@LogTag("NavigationViewWithTextActivity")
@IsFullScreen(false)
class NavigationViewWithTextActivity : BaseFontActivity() {

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_navigation_view_with_text
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nv.apply {
            setTextNext("Next")
            setTextPrev("Prev Prev Prev")
            setTextSize(dpPrev = 22f, dpText = 18f, dpNext = 22f)
            colorOn = LAppResource.getColor(R.color.red)
            colorOff = LAppResource.getColor(R.color.gray)
            tv?.setTextColor(Color.BLACK)
        }

        val stringList = ArrayList<String>()
        for (i in 0..9) {
            stringList.add("Item $i")
        }

        nv.setStringList(stringList)
        nv.setNVCallback(object : LTextNavigationView.NVCallback {
            @SuppressLint("SetTextI18n")
            override fun onIndexChange(index: Int, s: String?) {
                tvMsg?.text = "$index -> $s"
            }
        })
        bt0.setOnClickListener { nv.setCurrentIndex(0) }
        bt1.setOnClickListener { nv.setCurrentIndex(stringList.size - 1) }
        bt2.setOnClickListener { nv.setCurrentIndex(2) }
    }
}
