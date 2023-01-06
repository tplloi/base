package vn.loitp.a.cv.progress.comparingPerformanceBar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.core.view.isVisible
import com.loitp.annotation.IsAutoAnimation
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.ext.openUrlInBrowser
import com.loitp.core.utilities.LUIUtil
import kotlinx.android.synthetic.main.a_comparing_performance_bar.*
import vn.loitp.R

@LogTag("ComparingPerformanceBarActivity")
@IsFullScreen(false)
@IsAutoAnimation(true)
class ComparingPerformanceBarActivityFont : BaseActivityFont() {

    override fun setLayoutResourceId(): Int {
        return R.layout.a_comparing_performance_bar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    @SuppressLint("SetTextI18n")
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
                            url = "https://github.com/cliffgr/ComparingPerformanceBar"
                        )
                    }
                )
                it.isVisible = true
                it.setImageResource(R.drawable.ic_baseline_code_48)
            }
            this.tvTitle?.text = ComparingPerformanceBarActivityFont::class.java.simpleName
        }

        percentageProgressBar.setProgress(30.0f)
        valuesProgressBar.setValues(100.0f, 20.0f)
        valuesProgressBarPercent.setValues(10.0f, 7.0f)
    }
}
