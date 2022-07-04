package vn.loitp.app.activity.customviews.layout.shadowLayout

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.loitpcore.annotation.IsFullScreen
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFontActivity
import com.loitpcore.core.utilities.LAppResource
import com.loitpcore.core.utilities.LSocialUtil
import com.loitpcore.core.utilities.LUIUtil
import com.loitpcore.utils.util.ConvertUtils
import kotlinx.android.synthetic.main.activity_shadow_layout.*
import vn.loitp.app.R

@LogTag("ShadowLayoutActivity")
@IsFullScreen(false)
class ShadowLayoutActivity : BaseFontActivity(), View.OnClickListener {

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_shadow_layout
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
            this.ivIconRight?.let {
                LUIUtil.setSafeOnClickListenerElastic(
                    view = it,
                    runnable = {
                        LSocialUtil.openUrlInBrowser(
                            context = context,
                            url = "https://github.com/lijiankun24/ShadowLayout"
                        )
                    }
                )
                it.isVisible = true
                it.setImageResource(R.drawable.ic_baseline_code_48)
            }
            this.viewShadow?.isVisible = true
            this.tvTitle?.text = ShadowLayoutActivity::class.java.simpleName
        }
        tvChangeOval.setOnClickListener(this)
        tvChangeRadius.setOnClickListener(this)
        tvChangeRectangle.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v) {
            tvChangeOval -> slOval.setShadowColor(LAppResource.getColor(R.color.black50))
            tvChangeRadius -> slRectangle.setShadowColor(Color.parseColor("#EE00FF7F"))
            tvChangeRectangle -> slRadius.setShadowRadius(ConvertUtils.dp2px(12f).toFloat())
        }
    }
}