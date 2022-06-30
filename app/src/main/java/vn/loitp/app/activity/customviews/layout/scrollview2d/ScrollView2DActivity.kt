package vn.loitp.app.activity.customviews.layout.scrollview2d

import android.os.Bundle
import androidx.core.view.isVisible
import com.loitpcore.annotation.IsFullScreen
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFontActivity
import com.loitpcore.core.utilities.LUIUtil
import kotlinx.android.synthetic.main.activity_layout_scrollview_2d.*
import vn.loitp.app.R

@LogTag("ScrollView2DActivity")
@IsFullScreen(false)
class ScrollView2DActivity : BaseFontActivity() {

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_layout_scrollview_2d
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        lActionBar.apply {
            LUIUtil.setSafeOnClickListenerElastic(
                view = this.ivIconLeft,
                runnable = {
                    onBackPressed()
                }
            )
            this.ivIconRight?.isVisible = false
            this.viewShadow?.isVisible = true
            this.tvTitle?.text = ScrollView2DActivity::class.java.simpleName
        }
        twoDScrollView.setScrollChangeListener { _, x, y, oldX, oldY ->
            logD("setScrollChangeListener $x - $y - $oldX - $oldY")
        }
        LUIUtil.setDelay(
            mls = 2000,
            runnable = Runnable {
                twoDScrollView.smoothScrollTo(300, 300)
            }
        )
    }
}
