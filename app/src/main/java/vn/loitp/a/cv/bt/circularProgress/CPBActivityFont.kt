package vn.loitp.a.cv.bt.circularProgress

import android.os.Bundle
import androidx.core.view.isVisible
import com.loitp.annotation.IsAutoAnimation
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.ext.openUrlInBrowser
import com.loitp.core.ext.setSafeOnClickListener
import com.loitp.core.utilities.LUIUtil
import kotlinx.android.synthetic.main.a_cpb.*
import vn.loitp.R

@LogTag("CPBActivity")
@IsFullScreen(false)
@IsAutoAnimation(false)
class CPBActivityFont : BaseActivityFont() {

    override fun setLayoutResourceId(): Int {
        return R.layout.a_cpb
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
                    onBaseBackPressed()
                }
            )
            this.ivIconRight?.let {
                LUIUtil.setSafeOnClickListenerElastic(
                    view = it,
                    runnable = {
                        context.openUrlInBrowser(
                            url = "https://github.com/dmytrodanylyk/circular-progress-button"
                        )
                    }
                )
                it.isVisible = true
                it.setImageResource(R.drawable.ic_baseline_code_48)
            }
            this.tvTitle?.text = CPBActivityFont::class.java.simpleName
        }

        bt1.setSafeOnClickListener {
            launchActivity(Sample1ActivityFont::class.java)
        }
        bt2.setSafeOnClickListener {
            launchActivity(Sample2ActivityFont::class.java)
        }
        bt3.setSafeOnClickListener {
            launchActivity(Sample3ActivityFont::class.java)
        }
        bt4.setSafeOnClickListener {
            launchActivity(Sample4ActivityFont::class.java)
        }
        bt5.setSafeOnClickListener {
            launchActivity(Sample5ActivityFont::class.java)
        }
    }
}
